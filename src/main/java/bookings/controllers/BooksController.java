package bookings.controllers;

import bookings.models.Book;
import bookings.models.BookDAO;
import bookings.util.Views;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BooksController implements Initializable {
    public VBox booksColumn;
    ObservableList<Book> books;
    ArrayList<Node> bookRows;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            books = BookDAO.getBooks();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        bookRows = new ArrayList<>();

        books.forEach(book -> {
            // Initialize controller
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.BOOK_ITEM));


            try {
                bookRows.add(fxmlLoader.load());
                // Pass parameters to controller
                BookItemController bookItemController = fxmlLoader.getController();
                bookItemController.setBookId(String.valueOf(book.id()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        bookRows.forEach(row -> {
            booksColumn.getChildren().add(row);

        });

    }
}
