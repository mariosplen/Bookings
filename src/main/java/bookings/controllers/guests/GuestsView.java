package bookings.controllers.guests;

import bookings.models.Guest;
import bookings.models.GuestDAO;
import bookings.util.Views;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GuestsView implements Initializable {
    @FXML
    private TableView<Guest> guestsTV;
    @FXML
    private TableColumn<Guest, Number> guestsTVId;
    @FXML
    private TableColumn<Guest, String> guestsTVName;
    @FXML
    private TableColumn<Guest, String> guestsTVPhone;
    @FXML
    private TableColumn<Guest, String> guestsTVEmail;
    @FXML
    private TableColumn<Guest, Boolean> guestsTVisGroup;




    private void tableViewValues() throws SQLException, ClassNotFoundException {
        ObservableList<Guest> guestsObservableList = GuestDAO.getGuests();
        guestsTVId.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().id()));
        guestsTVName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().name()));
        guestsTVPhone.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().phone()));
        guestsTVEmail.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().email()));
        guestsTVisGroup.setCellValueFactory(p -> new SimpleBooleanProperty(p.getValue().isGroup()));
        guestsTV.setItems(guestsObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            tableViewValues();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void onNewGuestClicked() throws IOException {
        // Initialize controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.GUEST_FORM));
        Scene scene = new Scene(loader.load());

        // Set scene to new window
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
}


