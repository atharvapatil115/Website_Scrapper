
// Importing the system defined packages
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
// import java.util.Collections;
// import java.util.Comparator;
import java.util.List;

// Importing user-defined packages
import Backend.Website_scrapperBackend;
import Backend.Book;

// Frontend 
public class Website_scrapper {

    private JFrame frame;
    private JTable table;
    private Website_scrapperBackend tool;

    public Website_scrapper() {
        tool = new Website_scrapperBackend();
        initialize();
    }

    private void initialize() {
        // Create the main frame
        frame = new JFrame("Data Scraper Tool");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Create a table to display the books
        String[] columnNames = { "Sr no.","Title", "Price" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create a panel for buttons with vertical layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Button to scrape data
        JButton btnScrape = new JButton("View Data");
        btnScrape.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnScrape.getPreferredSize().height));
        btnScrape.addActionListener(e -> loadData()); // parameter are passed in lambda expression
        buttonPanel.add(btnScrape);

        // Button to find the book with the cheapest size
        JButton btnCheapestBook = new JButton("Show Cheapest Book");
        btnCheapestBook.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnCheapestBook.getPreferredSize().height));
        btnCheapestBook.addActionListener(e -> displayCheapestBook());
        buttonPanel.add(btnCheapestBook);

        // Button to clear the table
        JButton clearTable = new JButton("Clear Table");
        clearTable.setMaximumSize(new Dimension(Integer.MAX_VALUE, clearTable.getPreferredSize().height));
        clearTable.addActionListener(e -> ClearTable());
        buttonPanel.add(clearTable);

        // Add the button panel to the SOUTH region
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Show the frame
        frame.setVisible(true);
    }

    // Function which loads the data in the table

    private void loadData() {
        int srNO = 1;
        List<Book> books = tool.scrapeData();
        tool.storeData(books);
        tool.comparePrices(books);

        // Clear the table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        // Add book data to the table
        for (Book book : books) {
            
            String[] rowData = { String.valueOf(srNO++) , book.getTitle(), book.getPrice() };
            model.addRow(rowData);
        }
    }

    // Main Function
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Website_scrapper window = new Website_scrapper();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

        // Function to display the cheapest book in the table
    private void displayCheapestBook() {
        List<Book> books = tool.scrapeData();
        
        Book cheapestBook = tool.getCheapestBook(books);
        System.out.println("Cheapest book shown");

        // Display cheapest book information
        JOptionPane.showMessageDialog(frame,
                "Cheapest Book:\nTitle: " + cheapestBook.getTitle() + "\nPrice: " + cheapestBook.getPrice(),
                "Cheapest Book", JOptionPane.INFORMATION_MESSAGE);
    }

    private void ClearTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        System.out.println("Table cleared!");
    }

}
