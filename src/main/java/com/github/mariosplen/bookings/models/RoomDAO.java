package com.github.mariosplen.bookings.models;


import com.github.mariosplen.bookings.util.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RoomDAO {

    public static ObservableList<Room> getRooms() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM rooms";
        ResultSet rs = DBManager.dbExecuteQuery(query);

        ObservableList<Room> rooms = getRoomsFromRs(rs);
        rs.close();
        return rooms;
    }


    public static Room getRoomFromRs(ResultSet rs) throws SQLException {
        return new Room(
                rs.getInt("id"),
                rs.getString("category")
        );
    }

    public static ObservableList<Room> getRoomsFromRs(ResultSet rs) throws SQLException {
        ObservableList<Room> rooms = FXCollections.observableArrayList();
        while (rs.next()) {
            rooms.add(getRoomFromRs(rs));
        }
        return rooms;
    }

    public static List<Room> getAvailableForDate(LocalDate checkIn, LocalDate checkOut, String cat) throws SQLException, ClassNotFoundException {


        String query = """
                SELECT * FROM rooms r
                WHERE r.category = ?
                  AND r.id NOT IN (
                    SELECT b.room_id FROM books b
                    WHERE (b.check_in <= ? AND b.check_out >= ? )
                       OR (b.check_in <= ? AND b.check_out >= ? )
                       OR (b.check_in >= ? AND b.check_out <= ? )
                )
                """;


        ResultSet rs = DBManager.dbExecuteQuery(query,
                cat,
                checkIn.toString(),
                checkOut.toString(),
                checkOut.toString(),
                checkIn.toString(),
                checkIn.toString(),
                checkOut.toString()
        );
        List<Room> rooms = getRoomsFromRs(rs);
        rs.close();
        return rooms;

    }

}
