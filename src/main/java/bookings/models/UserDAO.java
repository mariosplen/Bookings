package bookings.models;

import bookings.util.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {


    public static User getUser(String username) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM users WHERE username = ?";
        ResultSet rsUser = DBManager.dbExecuteQuery(selectStmt, username);

        if (rsUser.next()) {
            return getUserFromRs(rsUser);
        }
        return null;
    }

    public static ObservableList<User> getUsers() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM users";
        ResultSet rsUsers = DBManager.dbExecuteQuery(selectStmt);

        return getUsersFromRS(rsUsers);
    }

    public static User login(String username, String password) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM users WHERE username = ? AND password = ?";
        ResultSet rsUser = DBManager.dbExecuteQuery(selectStmt, username, password);

        if (rsUser.next()) {
            return getUserFromRs(rsUser);
        }
        return null;

    }

    public static User getUserFromRs(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setPrivLevel(rs.getInt("privilege_level"));

        return user;
    }

    public static ObservableList<User> getUsersFromRS(ResultSet rs) throws SQLException {
        ObservableList<User> userList = FXCollections.observableArrayList();
        while (rs.next()) {
            userList.add(getUserFromRs(rs));
        }
        return userList;
    }
}
