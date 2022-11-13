package bookings.controllers.books;

import bookings.models.Book;
import bookings.models.BookDAO;
import bookings.util.Views;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BooksController implements Initializable {
    @FXML
    private VBox booksColumn;
    ObservableList<Book> books;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            books = BookDAO.getBooks();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        books.forEach(book -> {
            // Initialize controller
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.BOOK_ITEM));


            try {
                booksColumn.getChildren().add(fxmlLoader.load());
                // Pass parameters to controller
                BookItemController bookItemController = fxmlLoader.getController();
                bookItemController.setBookId(String.valueOf(book.id()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }
}
