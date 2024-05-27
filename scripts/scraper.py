import requests
from bs4 import BeautifulSoup
from telegram import Bot
from twilio.rest import Client
import os

def get_deals():
    url = "https://www.amazon.in/deals"
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3'}
    response = requests.get(url, headers=headers)
    soup = BeautifulSoup(response.content, 'html.parser')

    deals = []

    # The structure of the Amazon deals page
    # This might need adjustments based on the actual HTML structure
    deal_sections = soup.find_all('div', {'class': 'DealGridItem-module__dealItem_1HSkc'})
    for section in deal_sections:
        title_element = section.find('span', {'class': 'DealTitle-module__truncate_sWbxETx42kPp1zkeJLuo'})
        price_element = section.find('span', {'class': 'Price-module__value'})
        link_element = section.find('a', {'class': 'a-link-normal'})

        if title_element and price_element and link_element:
            title = title_element.get_text().strip()
            price = price_element.get_text().strip()
            link = "https://www.amazon.in" + link_element['href']

            deals.append({
                'title': title,
                'price': price,
                'link': link
            })

    return deals

def send_telegram_message(token, chat_id, message):
    bot = Bot(token=token)
    bot.send_message(chat_id=chat_id, text=message)

if __name__ == "__main__":
    # Get environment variables
    telegram_token = os.getenv('TELEGRAM_TOKEN')
    telegram_chat_id = os.getenv('TELEGRAM_CHAT_ID')

    # Get deals
    deals = get_deals()

    # Send deals via Telegram
    for deal in deals:
        message = f"{deal['title']} - {deal['price']} - {deal['link']}"
        send_telegram_message(telegram_token, telegram_chat_id, message)

