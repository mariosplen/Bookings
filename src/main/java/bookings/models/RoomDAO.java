package bookings.models;


import bookings.util.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDAO {

    public static ObservableList<Room> getRooms() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM rooms";
        ResultSet rs = DBManager.dbExecuteQuery(query);

        ObservableList<Room> rooms = getRoomsFromRs(rs);
        rs.close();
        return rooms;
    }


    public static Room getRoomFromRs(ResultSet rs) throws SQLException {
        return new Room(rs.getInt("id"), rs.getString("category"), rs.getBoolean("clean"), rs.getBoolean("available"));
    }

    public static ObservableList<Room> getRoomsFromRs(ResultSet rs) throws SQLException {
        ObservableList<Room> rooms = FXCollections.observableArrayList();
        while (rs.next()) {
            rooms.add(getRoomFromRs(rs));
        }
        return rooms;
    }
}
