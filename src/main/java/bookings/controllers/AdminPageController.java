package bookings.controllers;

import bookings.models.Rooms;
import bookings.models.RoomsDAO;
import bookings.models.User;
import bookings.models.UserDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;


public class AdminPageController {

    public Label usernameL;
    @FXML
    private TableView<Rooms> roomsTableView;
    @FXML
    private TableColumn<Rooms, Integer> roomsIdTV;
    @FXML
    private TableColumn<Rooms, String> categoryTV;

    String userName;


    public void GetUserID(String userName) {
        this.userName = userName;

    }

    // This runs after supplying the Controller with the username(id)
    public void secondInitialize() throws SQLException, ClassNotFoundException {

        User user = UserDAO.searchUser(userName);
        if (user != null) {
            usernameL.setText(user.getName());
            tableViewValues();
        }


    }

    public void tableViewValues() throws SQLException, ClassNotFoundException {
        ObservableList<Rooms> observableList = RoomsDAO.returnRooms();
        roomsIdTV.setCellValueFactory(new PropertyValueFactory<Rooms, Integer>("id"));
        categoryTV.setCellValueFactory(new PropertyValueFactory<Rooms, String>("category"));
        roomsTableView.setItems(observableList);
    }


}