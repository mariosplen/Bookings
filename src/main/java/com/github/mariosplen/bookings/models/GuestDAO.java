package com.github.mariosplen.bookings.models;


import com.github.mariosplen.bookings.util.DBManager;
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
        return new Guest(
                rs.getString("name"),
                rs.getString("phone"),
                rs.getString("email"));
    }

    public static ObservableList<Guest> getGuestsFromRs(ResultSet rs) throws SQLException {
        ObservableList<Guest> guests = FXCollections.observableArrayList();
        while (rs.next()) {
            guests.add(getGuestFromRs(rs));
        }
        return guests;
    }


    public static void addGuest(String name, String phone, String email) throws SQLException, ClassNotFoundException {
        String query = """
                INSERT INTO guests(name, phone, email)
                VALUES(?,?,?)
                """;
        DBManager.dbExecuteUpdate(query, name, phone, email);

    }

    public static void updateData(String name, String perm, String value) throws SQLException, ClassNotFoundException {

        String   query = """
                    UPDATE guests SET '%s' = ?
                    WHERE name = ?
                    """.formatted(perm);
            DBManager.dbExecuteUpdate(query, value, name);

    }

}
