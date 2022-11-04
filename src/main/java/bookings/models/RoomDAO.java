package bookings.models;


import bookings.util.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDAO {

    public static ObservableList<Room> getRooms() throws SQLException, ClassNotFoundException {
        String queryRooms = "SELECT * FROM rooms";
        ResultSet rs = DBManager.dbExecuteQuery(queryRooms);

        return getRoomsFromRs(rs);
    }


    public static Room getRoomFromRs(ResultSet rs) throws SQLException {

        return new Room(rs.getInt("id"), rs.getString("category"), rs.getBoolean("clean"), rs.getBoolean("available"));
    }

    public static ObservableList<Room> getRoomsFromRs(ResultSet rs) throws SQLException {
        ObservableList<Room> roomList = FXCollections.observableArrayList();
        while (rs.next()) {
            roomList.add(getRoomFromRs(rs));
        }

        return roomList;
    }


}
