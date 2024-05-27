import os
import requests
from bs4 import BeautifulSoup
from telegram import Bot
import logging

# Configure logging
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

def scrape_deals(url):
    """
    Scrapes deal information from an ecommerce website.

    Args:
    - url (str): The URL of the website to scrape.

    Returns:
    - list: A list of dictionaries, each containing deal information (title, price, link).
    """
    logger.info(f"Scraping deals from {url}")

    # Send a GET request to the URL
    response = requests.get(url)

    # Check if the request was successful
    if response.status_code != 200:
        logger.error(f"Failed to retrieve data from {url}. Status code: {response.status_code}")
        return []

    # Parse the HTML content of the webpage
    soup = BeautifulSoup(response.content, 'html.parser')

    # Find elements containing deal information
    # These selectors might need to be adjusted based on the specific website's HTML structure
    deal_elements = soup.find_all('div', class_='deal')  # Example: <div class="deal">

    logger.info(f"Found {len(deal_elements)} deal elements on {url}")

    deals = []

    # Extract deal information from each element
    for deal_element in deal_elements:
        title = deal_element.find('h2').text.strip() if deal_element.find('h2') else 'Title not found'
        price = deal_element.find('span', class_='price').text.strip() if deal_element.find('span', class_='price') else 'Price not found'
        link = deal_element.find('a')['href'] if deal_element.find('a') else 'Link not found'

        # Add deal to list of deals
        deals.append({
            'title': title,
            'price': price,
            'link': link
        })

    logger.info(f"Scraped {len(deals)} deals from {url}")

    return deals

def send_telegram_message(token, chat_id, message):
    """
    Sends a message to a Telegram chat.

    Args:
    - token (str): The Telegram bot API token.
    - chat_id (str): The ID of the Telegram chat.
    - message (str): The message to send.
    """
    bot = Bot(token=token)
    bot.send_message(chat_id=chat_id, text=message)
    logger.info(f"Sent message to Telegram chat: {message}")

if __name__ == "__main__":
    # Telegram configuration
    telegram_token = os.getenv('TELEGRAM_TOKEN')
    telegram_chat_id = os.getenv('TELEGRAM_CHAT_ID')

    # Get the URL of the ecommerce website from user input
    url = "https://www.amazon.in/deals"
    logger.info(f"Entered URL: {url}")

    # Scrape deals from the website
    scraped_deals = scrape_deals(url)

    # Send scraped deals to Telegram chat
    for deal in scraped_deals:
        message = f"{deal['title']} - {deal['price']} - {deal['link']}"
        send_telegram_message(telegram_token, telegram_chat_id, message)

    logger.info("Script execution completed.")
