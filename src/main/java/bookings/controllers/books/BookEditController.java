package bookings.controllers.books;

import bookings.models.BookDAO;
import bookings.util.Views;
import javafx.application.Platform;
import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookEditController implements Initializable {
    public Label msg;
    int bookId;

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            msg.setText(String.valueOf(bookId));
        });
    }


    public void onDeleteClicked() throws SQLException, ClassNotFoundException {
        BookDAO.deleteBook(bookId);

        // Refresh mainPane by re-creating loader
        Window.getWindows().forEach( window -> {
            Parent p = window.getScene().getRoot();
            if(p.lookup("#mainPane")!=null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.BOOKS));
                try {
                    ((BorderPane)p).setCenter(loader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
