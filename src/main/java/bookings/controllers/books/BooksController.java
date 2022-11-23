package bookings.controllers.books;

import bookings.models.Book;
import bookings.models.BookDAO;
import bookings.util.Views;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BooksController implements Initializable {


    @FXML
    private TableView<Book> tempBooksTV;

    @FXML
    private TableColumn<Book, Number>
            id,
            room_id,
            guest_id,
            door_price,
            total_price;
    @FXML
    private TableColumn<Book, String>
            check_in,
            check_out,
            status,
            date,
            payment_method,
            card_vcc,
            card_num;
    @FXML
    private TableColumn<Book, Boolean>
            prepayment_offer,
            low_occupancy_offer,
            from_Phone_prepayment_charge,
            summer_charge,
            christmas_charge,
            easter_charge,
            event_charge;

    @FXML
    private VBox booksColumn;
    ObservableList<Book> books;


    private void tableViewValues() throws SQLException, ClassNotFoundException {
        ObservableList<Book> roomObservableList = BookDAO.getBooks();
        id.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().id()));
        guest_id.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().guestId()));
        room_id.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().roomId()));
        check_in.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().checkIn().toString()));
        check_out.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().checkOut().toString()));
        status.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().status()));
        date.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().date().toString()));
        payment_method.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().paymentMethod()));
        prepayment_offer.setCellValueFactory(p -> new SimpleBooleanProperty(p.getValue().isUsingPrepaymentOffer()));
        low_occupancy_offer.setCellValueFactory(p -> new SimpleBooleanProperty(p.getValue().isUsingLowOccupancyOffer()));
        from_Phone_prepayment_charge.setCellValueFactory(p -> new SimpleBooleanProperty(p.getValue().isChargedFromPhonePrepayment()));
        summer_charge.setCellValueFactory(p -> new SimpleBooleanProperty(p.getValue().isSummerCharged()));
        christmas_charge.setCellValueFactory(p -> new SimpleBooleanProperty(p.getValue().isChristmasCharged()));
        easter_charge.setCellValueFactory(p -> new SimpleBooleanProperty(p.getValue().isEasterCharged()));
        event_charge.setCellValueFactory(p -> new SimpleBooleanProperty(p.getValue().isEventCharged()));
        door_price.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().doorPrice()));
        total_price.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().totalPrice()));
        card_num.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().cardNumber()));
        card_vcc.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().cardVCC()));
        tempBooksTV.setItems(roomObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            books = BookDAO.getBooks();
            tableViewValues();
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
