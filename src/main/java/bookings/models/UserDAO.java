package bookings.models;

import bookings.util.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {


    public static User getUser(String username) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE username = ?";
        ResultSet rs = DBManager.dbExecuteQuery(query, username);

        if (rs.next()) {
            User user = getUserFromRs(rs);
            rs.close();
            return user;
        }
        rs.close();
        return null;
    }

    public static ObservableList<User> getUsers() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users";
        ResultSet rs = DBManager.dbExecuteQuery(query);

        ObservableList<User> users = getUsersFromRS(rs);
        rs.close();
        return users;
    }

    public static User login(String username, String password) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        ResultSet rs = DBManager.dbExecuteQuery(query, username, password);

        if (rs.next()) {
            User user = getUserFromRs(rs);
            rs.close();
            return user;
        }
        rs.close();
        return null;

    }

    public static User getUserFromRs(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getBoolean("basic_functions"),
                rs.getBoolean("view_info"),
                rs.getBoolean("manage_guests"),
                rs.getBoolean("manage_users")
        );
    }

    public static ObservableList<User> getUsersFromRS(ResultSet rs) throws SQLException {
        ObservableList<User> users = FXCollections.observableArrayList();
        while (rs.next()) {
            users.add(getUserFromRs(rs));
        }
        return users;
    }
}
