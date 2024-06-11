package Backend;

public class Book {
    private String title;
    private String price;

    public Book(String title, String price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
