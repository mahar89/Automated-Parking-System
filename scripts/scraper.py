import requests
import logging
from telegram import Bot

# Configure logging
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

def scrape_deals(api_url):
    deals = []

    try:
        # Send a GET request to the API endpoint
        response = requests.get(api_url)

        # Check if the request was successful
        if response.status_code != 200:
            logger.error(f"Failed to retrieve data from {api_url}. Status code: {response.status_code}")
            return deals

        # Parse the JSON response
        json_data = response.json()

        # Extract deal information from the response
        for product in json_data['products']:
            brand_name = product['brandTypeName'].lower()  # Convert to lowercase for case-insensitive comparison
            if brand_name == 'nike':
                title = product['name']
                original_price = product['price']['value']
                offer_price = product['offerPrice']['value']
                discount_percentage = product['discountPercent'].replace('% off', '')
                product_url = f"https://www.ajio.com{product['url']}"

                # Check if the discount is 70% or more
                if int(discount_percentage) >= 70:
                    deals.append({
                        'title': title,
                        'original_price': original_price,
                        'offer_price': offer_price,
                        'discount_percentage': discount_percentage,
                        'product_url': product_url
                    })

        logger.info(f"Scraped {len(deals)} Nike brand products with a discount of 70% or more.")

    except Exception as e:
        logger.error(f"An error occurred: {str(e)}")

    return deals

def send_telegram_message(token, chat_id, message):
    try:
        # Initialize the Telegram bot
        bot = Bot(token=token)

        # Send message to Telegram chat
        bot.send_message(chat_id=chat_id, text=message)

        logger.info("Message sent to Telegram chat.")
    except Exception as e:
        logger.error(f"Failed to send message to Telegram chat: {str(e)}")

if __name__ == "__main__":
    # Telegram configuration
    telegram_token = os.getenv('TELEGRAM_TOKEN')
    telegram_chat_id = os.getenv('TELEGRAM_CHAT_ID')

    # API URL for Nike brand products with pagination
    api_url = "https://www.ajio.com/api/category/83?currentPage=2&pageSize=45&format=json"

    # Scrape Nike brand products with a discount of 70% or more
    scraped_deals = scrape_deals(api_url)

    # Send scraped deals to Telegram chat
    for i, deal in enumerate(scraped_deals, 1):
        message = (
            f"Deal {i}:\n"
            f"Title: {deal['title']}\n"
            f"Original Price: ₹{deal['original_price']}\n"
            f"Offer Price: ₹{deal['offer_price']}\n"
            f"Discount Percentage: {deal['discount_percentage']}%\n"
            f"Product URL: {deal['product_url']}"
        )
        send_telegram_message(telegram_token, telegram_chat_id, message)
