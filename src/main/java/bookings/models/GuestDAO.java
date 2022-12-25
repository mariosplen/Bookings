package bookings.models;


import bookings.util.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestDAO {

    public static ObservableList<Guest> getGuests() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM guests";
        ResultSet rs = DBManager.dbExecuteQuery(query);

        ObservableList<Guest> guests = getGuestsFromRs(rs);
        rs.close();
        return guests;
    }


    public static Guest getGuestFromRs(ResultSet rs) throws SQLException {
        return new Guest(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("phone"),
                rs.getString("email"),
                rs.getBoolean("group"));
    }

    public static ObservableList<Guest> getGuestsFromRs(ResultSet rs) throws SQLException {
        ObservableList<Guest> guests = FXCollections.observableArrayList();
        while (rs.next()) {
            guests.add(getGuestFromRs(rs));
        }
        return guests;
    }


    public static void addGuest(String name, String phone, String email, Boolean isGroup) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO guests(name, phone, email, \"group\") VALUES(?,?,?,?)";
        DBManager.dbExecuteUpdate(query, name, phone, email, isGroup);

    }

    public static void updateData(int id, String perm, String value) throws SQLException, ClassNotFoundException {
        String query;
        if (perm.equals("group")) {
            query = "UPDATE `guests` SET '%s' = '%s' WHERE id = ? ".formatted(perm, Boolean.parseBoolean(value) ? 1 : 0);
        } else {
            query = "UPDATE `guests` SET '%s' = '%s' WHERE id = '%s' ".formatted(perm, value, id);
        }


        DBManager.dbExecuteUpdate(query);
    }

}
