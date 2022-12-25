package bookings.models;


import bookings.util.DBManager;
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
        return new Room(rs.getInt("id"), rs.getString("category"), rs.getString("status"), rs.getBoolean("available"));
    }

    public static ObservableList<Room> getRoomsFromRs(ResultSet rs) throws SQLException {
        ObservableList<Room> rooms = FXCollections.observableArrayList();
        while (rs.next()) {
            rooms.add(getRoomFromRs(rs));
        }
        return rooms;
    }

    public static List<Room> getAvailableForDate(LocalDate checkIn, LocalDate checkOut, String cat) throws SQLException, ClassNotFoundException {
        System.out.println(checkIn.toString());
        String query = "SELECT r.* FROM rooms r LEFT JOIN books b ON r.id = b.room_id AND (b.check_in <= '%s' AND b.check_out > '%s') WHERE r.category = '%s' AND (b.room_id IS NULL OR b.status IN ('Done', 'Canceled'))".formatted(checkOut.toString(), checkIn.toString(), cat);


        ResultSet rs = DBManager.dbExecuteQuery(query);
        List<Room> rooms = getRoomsFromRs(rs);
        rs.close();
        return rooms;

    }
}
