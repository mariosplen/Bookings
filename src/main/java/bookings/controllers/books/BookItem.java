package bookings.controllers.books;

import bookings.util.Views;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BookItem {

    public TextField bookId;

    public void setBookId(String bookId) {
        this.bookId.setText(bookId);
    }

    public void onEditClicked(ActionEvent actionEvent) throws IOException {


        // Finds current bookId by going backwards to the node tree
        int bookId = Integer.parseInt(
                ((TextField)
                        ((Button)actionEvent.getSource())
                                .getParent().getChildrenUnmodifiable().get(0))
                        .getText()
        );

        // Initialize controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.BOOK_EDIT));

        Scene scene = new Scene(loader.load());

        // Pass parameters to controller
        BookEdit bookEdit = loader.getController();
        bookEdit.setBookId(bookId);

        // Set scene to new window
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

    }
}
