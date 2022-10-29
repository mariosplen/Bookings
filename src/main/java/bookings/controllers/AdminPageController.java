package bookings.controllers;

import bookings.models.Rooms;
import bookings.models.RoomsDAO;
import bookings.models.User;
import bookings.models.UserDAO;
import bookings.util.PageController;
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

public class AdminPageController implements PageController, Initializable {

    public Label usernameL;
    @FXML
    private TableView<Rooms> roomsTV;
    @FXML
    private TableColumn<Rooms, Integer> roomsTVId;
    @FXML
    private TableColumn<Rooms, String> roomsTVCategory;
    private String username;

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void lateInitialize() throws SQLException, ClassNotFoundException {
        User user = UserDAO.searchUser(username);
        if (user != null) {
            usernameL.setText(user.getName());
        }
    }


    public void tableViewValues() throws SQLException, ClassNotFoundException {
        ObservableList<Rooms> roomsObservableList = RoomsDAO.returnRooms();
        roomsTVId.setCellValueFactory(new PropertyValueFactory<>("id"));
        roomsTVCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        roomsTV.setItems(roomsObservableList);
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