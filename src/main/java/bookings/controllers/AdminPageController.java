package bookings.controllers;

import bookings.models.User;
import bookings.models.UserDAO;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class AdminPageController {
    public Label usernameL;
    String userName;

    public void GetUserID(String userName) {
        this.userName = userName;

    }

    // This runs after supplying the Controller with the username(id)
    public void secondInitialize() throws SQLException, ClassNotFoundException {


        User user = UserDAO.searchUser(userName);
        if (user != null) {
            usernameL.setText(user.getName());
        }


    }
}
