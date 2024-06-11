package Backend;   // Declared this file as a package
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Website_scrapperBackend {

        // Method to Scrape/ Extract the data from the website
    public List<Book> scrapeData() {
        List<Book> books = new ArrayList<>();
        try {
            String webUrl = "http://books.toscrape.com/catalogue/category/books/historical-fiction_4/index.html";
            Document doc = Jsoup.connect(webUrl).get();

            Elements productNames = doc.select("article.product_pod h3 a");
            Elements productPrices = doc.select("article.product_pod p.price_color");

            for (int i = 0; i < productNames.size(); i++) {
                String bookTitle = productNames.get(i).attr("title");
                String bookPrice = productPrices.get(i).text();
                books.add(new Book(bookTitle, bookPrice));
            }

            System.out.println("Scraped " + books.size() + " books.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

        // Method to store the data into the database
    public void storeData(List<Book> books) {
        String url = "jdbc:mysql://localhost:3306/book_info";
        String username = "root";
        String password = "";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully.");

            String query = "INSERT INTO book(book_title, book_price) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);

            for (Book book : books) {
                stmt.setString(1, book.getTitle());
                stmt.setString(2, book.getPrice());
                stmt.executeUpdate();
                System.out.println("Inserted: " + book.getTitle() + " - " + book.getPrice());
            }
            stmt.close();
            con.close();
            System.out.println("Data stored successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Book> comparePrices(List<Book> books) {
        // Sort the books based on price
        Collections.sort(books, Comparator.comparing(Book::getPrice));

        return books;
    }
    public Book getCheapestBook(List<Book> books) {
        // Sort the books based on price
        Collections.sort(books, Comparator.comparing(Book::getPrice));

        // Return the first book (cheapest)
        return books.get(0);
    }

    
}
