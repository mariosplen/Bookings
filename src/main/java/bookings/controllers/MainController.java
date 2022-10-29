package bookings.controllers;

import bookings.models.Room;
import bookings.models.RoomDAO;
import bookings.models.User;
import bookings.models.UserDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public Label usernameL;
    @FXML
    private TableView<Room> roomsTV;
    @FXML
    private TableColumn<Room, Integer> roomsTVId;
    @FXML
    private TableColumn<Room, String> roomsTVCategory;
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public void lateInitialize() throws SQLException, ClassNotFoundException {
        User user = UserDAO.getUser(username);
        if (user != null) {
            usernameL.setText(user.getName());
        }
    }


    public void tableViewValues() throws SQLException, ClassNotFoundException {
        ObservableList<Room> roomObservableList = RoomDAO.getRooms();
        roomsTVId.setCellValueFactory(new PropertyValueFactory<>("id"));
        roomsTVCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
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