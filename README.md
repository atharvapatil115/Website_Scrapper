# Website Scraper in Java

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Jsoup](https://img.shields.io/badge/Jsoup-1.13.1-green)

## Overview

This project is a Java-based website scraper that extracts data from web pages using the Jsoup library. The scraped data is displayed in a user-friendly GUI built with Swing and can also be stored in a MySQL database. This tool is useful for gathering information, automating data collection, and integrating with other Java applications.

## Features

- **Efficient Data Extraction**: Scrape web pages and extract desired information efficiently.
- **User-Friendly GUI**: Display data in a table format with options to view, clear, and find the cheapest book.
- **Database Storage**: Store scraped data in a MySQL database.
- **Robust Parsing**: Uses Jsoup for robust HTML parsing and data extraction.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java Development Kit (JDK)**: Version 8 or higher
- **MySQL**: For storing scraped data
- **Internet Connection**: Required for fetching web pages

## Installation

To install and run this project on your local machine, follow these steps:

1. **Clone the Repository**:
    ```sh
    git clone https://github.com/your-username/website-scraper.git
    cd website-scraper
    ```

2. **Set Up the MySQL Database**:
    - Create a new database named `book_info`.
    - Create a table named `book` with the following structure:
      ```sql
      CREATE TABLE book (
          id INT AUTO_INCREMENT PRIMARY KEY,
          book_title VARCHAR(255) NOT NULL,
          book_price VARCHAR(50) NOT NULL
      );
      ```
    - Update the `storeData` method in `Website_scrapperBackend.java` with your MySQL username and password if different from the defaults.

3. **Compile the Project**:
    ```sh
    javac -d bin -sourcepath src src/Frontend/Website_scrapper.java src/Backend/Website_scrapperBackend.java src/Backend/Book.java
    ```

4. **Run the Application**:
    ```sh
    java -cp bin Frontend.Website_scrapper
    ```

## Usage

1. **Modify the Scraper**:
   - Open the project in your favorite IDE (e.g., IntelliJ IDEA, Eclipse).
   - Navigate to the `Website_scrapperBackend` class in the `Backend` package.
   - Adjust the URL and scraping logic as per your requirements.

2. **Run the Scraper**:
   - Execute the `Website_scrapper` class to start the GUI application.

3. **Use the GUI**:
   - **View Data**: Click the "View Data" button to scrape data and display it in the table.
   - **Show Cheapest Book**: Click the "Show Cheapest Book" button to find and display the cheapest book.
   - **Clear Table**: Click the "Clear Table" button to clear the table contents.
