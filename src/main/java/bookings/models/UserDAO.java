package bookings.models;

import bookings.util.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {


    public static User searchUser(String username) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM users WHERE username = \"" + username + "\"";
        ResultSet rsUser = DBManager.dbExecuteQuery(selectStmt);
        User user = getUserFromResultSet(rsUser);

        return user;
    }

    public static User loginUser(String username, String password) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM users WHERE username = \"" + username + "\" AND password = \"" + password + "\"";
        ResultSet rsUser = DBManager.dbExecuteQuery(selectStmt);
        User user = getUserFromResultSet(rsUser);

        return user;
    }

    private static User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = null;
        if (rs.next()) {
            user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("EMAIL"));
            user.setPhone(rs.getString("phone"));
            user.setIsAdmin(rs.getBoolean("is_admin"));
        }
        return user;
    }

    public static ObservableList<User> searchUsers() throws SQLException, ClassNotFoundException {
        String queryUser = "SELECT * FROM users";
        ResultSet rsUsers = DBManager.dbExecuteQuery(queryUser);
        ObservableList<User> userList = getUserList(rsUsers);
        return userList;
    }

    private static ObservableList<User> getUserList(ResultSet rs) throws SQLException {
        ObservableList<User> userList = FXCollections.observableArrayList();
        while (rs.next()) {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("EMAIL"));
            user.setPhone(rs.getString("phone"));
            user.setIsAdmin(rs.getBoolean("is_admin"));

            userList.add(user);
        }
        return userList;
    }


    public static void deleteUserWithId(String username) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "BEGIN\n" +
                        "   DELETE FROM users\n" +
                        "         WHERE username =" + username + ";\n" +
                        "   COMMIT;\n" +
                        "END;";
        DBManager.dbExecuteUpdate(updateStmt);
    }

    public static void insertUser(String username, String password, String name, String phone, String email, Boolean isAdmin) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "BEGIN\n" +
                        "INSERT INTO employees\n" +
                        "(username, password, name, email, phone, is_admin)\n" +
                        "VALUES\n" +
                        "('" + username + "', '" + password + "', '" + name + "','" + email + "', '" + phone + "', '" + isAdmin + "');\n" +
                        "END;";
        DBManager.dbExecuteUpdate(updateStmt);
    }

//
//    public static void updateEmpEmail (String empId, String empEmail) throws SQLException, ClassNotFoundException {
//        //Declare a UPDATE statement
//        String updateStmt =
//                "BEGIN\n" +
//                        "   UPDATE employees\n" +
//                        "      SET EMAIL = '" + empEmail + "'\n" +
//                        "    WHERE EMPLOYEE_ID = " + empId + ";\n" +
//                        "   COMMIT;\n" +
//                        "END;";
//        //Execute UPDATE operation
//        try {
//            DBUtil.dbExecuteUpdate(updateStmt);
//        } catch (SQLException e) {
//            System.out.print("Error occurred while UPDATE Operation: " + e);
//            throw e;
//        }
//    }

}
