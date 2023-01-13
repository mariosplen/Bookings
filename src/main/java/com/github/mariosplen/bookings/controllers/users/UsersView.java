package com.github.mariosplen.bookings.controllers.users;


import com.github.mariosplen.bookings.models.UserDAO;
import com.github.mariosplen.bookings.util.Views;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UsersView implements Initializable {
    public VBox usersVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            UserDAO.getUsers().forEach(user -> {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.USER_ITEM));

                try {
                    usersVBox.getChildren().add(fxmlLoader.load());
                    UserItem userItem = fxmlLoader.getController();
                    if (user.canManageUsers()) {
                        userItem.manageUsers.setFill(Color.web("0x5100FC", 1));
                    }
                    if (user.canDoBasic()) {
                        userItem.basicPerm.setFill(Color.web("0x5100FC", 1));
                    }
                    if (user.canManageGuests()) {
                        userItem.manageGuests.setFill(Color.web("0x5100FC", 1));
                    }
                    userItem.nameTxt.setText(user.username());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
