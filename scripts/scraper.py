import os
import logging
import scrapy
from scrapy_playwright.page import PageMethod
from telegram import Bot

# Configure logging
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

class Amztest1Spider(scrapy.Spider):
    name = "amztest1"

    def start_requests(self):
        url = 'https://www.amazon.in/deals'
        yield scrapy.Request(url, callback=self.parse,
            meta={
                "playwright" : True,
                "playwright_include_page" : True,
                "playwright_page_methods" :[
                        PageMethod('wait_for_selector', 'div#grid-main-container', timeout=0),
                        PageMethod("evaluate", "window.scrollBy(0, document.body.scrollHeight)"),
                    ],
                "playwright_context_kwargs": {"ignore_https_errors": True,},
                "playwright_page_goto_kwargs": {"wait_until": "networkidle",},
                "errback": self.errback,
            }
        )

    def parse(self, response):
        logger.info("Page loaded successfully. Scraping deals...")

        # Extract deal information
        deals = self.extract_deals(response)

        # Send scraped deals to Telegram chat
        self.send_telegram_message(deals)

    def extract_deals(self, response):
        # Extract deal information from the page
        # This logic should be adapted based on the specific HTML structure of the Amazon deals page
        # For demonstration purposes, we'll assume deals are stored in elements with class "deal"
        deal_elements = response.css('div.deal')

        deals = []
        for deal_element in deal_elements:
            title = deal_element.css('h2::text').get().strip() if deal_element.css('h2::text').get() else 'Title not found'
            price = deal_element.css('span.price::text').get().strip() if deal_element.css('span.price::text').get() else 'Price not found'
            link = response.urljoin(deal_element.css('a::attr(href)').get()) if deal_element.css('a::attr(href)').get() else 'Link not found'

            deals.append({
                'title': title,
                'price': price,
                'link': link
            })

        logger.info(f"Scraped {len(deals)} deals from the page")
        return deals

    def send_telegram_message(self, deals):
        # Telegram configuration
        telegram_token = os.getenv('TELEGRAM_TOKEN')
        telegram_chat_id = os.getenv('TELEGRAM_CHAT_ID')

        if not telegram_token or not telegram_chat_id:
            logger.error("Telegram token or chat ID not provided. Skipping sending messages.")
            return

        bot = Bot(token=telegram_token)

        # Send each deal as a separate message
        for deal in deals:
            message = f"{deal['title']} - {deal['price']} - {deal['link']}"
            bot.send_message(chat_id=telegram_chat_id, text=message)
            logger.info(f"Sent message to Telegram chat: {message}")

    def errback(self, failure):
        # Log errors if any occur during the request
        logger.error(f"Failed to load page: {failure.getErrorMessage()}")
