package bookings.controllers;

import bookings.application.DbConnection;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPageController {

    public Label usernameL;
    String userName;

    public void GetUserID(String userName) {
        this.userName = userName;

    }

    // This runs after supplying the Controller with the username(id)
    public void secondInitialize() throws SQLException {

        Connection con = DbConnection.Connection();
        if (con != null) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = (rs.getString("name"));
                usernameL.setText(name);
            }
        }

    }


}








