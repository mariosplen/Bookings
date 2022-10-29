package bookings.controllers;

import bookings.models.User;
import bookings.models.UserDAO;
import bookings.util.PageController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class UserPageController implements PageController {

    @FXML
    public Label usernameL;
    private String userName;

    @Override
    public void setUsername(String userName) {
        this.userName = userName;
    }

    @Override
    public void lateInitialize() throws SQLException, ClassNotFoundException {
        User user = UserDAO.searchUser(userName);
        if (user != null) {
            usernameL.setText(user.getName());
        }
    }
}








