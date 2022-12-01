package bookings.controllers.rooms;

import bookings.models.Room;
import bookings.models.RoomDAO;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RoomsView implements Initializable {

    @FXML
    private TableColumn<Room, String> roomsTVStatus;
    @FXML
    private TableColumn<Room, Boolean> roomsTVIsAvailable;
    @FXML
    private TableView<Room> roomsTV;
    @FXML
    private TableColumn<Room, Number> roomsTVId;
    @FXML
    private TableColumn<Room, String> roomsTVCategory;

    private void tableViewValues() throws SQLException, ClassNotFoundException {
        ObservableList<Room> roomObservableList = RoomDAO.getRooms();
        roomsTVId.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().id()));
        roomsTVCategory.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().category()));
        roomsTVStatus.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().status()));
        roomsTVIsAvailable.setCellValueFactory(p -> new SimpleBooleanProperty(p.getValue().isAvailable()));
        roomsTV.setItems(roomObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            tableViewValues();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}