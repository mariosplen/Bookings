package bookings.models;

import bookings.util.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {


// --Commented out by Inspection START (25/12/2022 4:12 μ.μ.):
//    public static User getUser(String username) throws SQLException, ClassNotFoundException {
//        String query = "SELECT * FROM users WHERE username = ?";
//        ResultSet rs = DBManager.dbExecuteQuery(query, username);
//
//        if (rs.next()) {
//            User user = getUserFromRs(rs);
//            rs.close();
//            return user;
//        }
//        rs.close();
//        return null;
//    }
// --Commented out by Inspection STOP (25/12/2022 4:12 μ.μ.)

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

    public static void changePass(String username, String newPass) throws SQLException, ClassNotFoundException {
        String query = "UPDATE users SET password = ? WHERE username = ? ";
        DBManager.dbExecuteUpdate(query, newPass, username);
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

    public static void addUser(
            String username,
            String password,
            Boolean canDoBasic,
            Boolean canViewInfo,
            Boolean canManageGuests,
            Boolean canManageUsers
    ) throws SQLException, ClassNotFoundException {
        String query = """
                INSERT INTO users(username, password, basic_functions, view_info, manage_guests, manage_users)
                VALUES(?,?,?,?,?,?)
                """;
        DBManager.dbExecuteUpdate(query,
                username,
                password,
                canDoBasic,
                canViewInfo,
                canManageGuests,
                canManageUsers
        );

    }

    public static void deleteUser(String username) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM users  WHERE username = ? ";
        DBManager.dbExecuteUpdate(query, username);
    }

    public static void switchPerm(String username, String perm) throws SQLException, ClassNotFoundException {

        String query = "UPDATE `users` SET %s = (( %s | 1) - (  %s & 1)) WHERE username = ? ".formatted(perm, perm, perm);

        DBManager.dbExecuteUpdate(query, username);
    }
}
