package bookings.models;


import bookings.util.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomsDAO {

    public static ObservableList<Rooms> returnRooms() throws SQLException, ClassNotFoundException {
        String queryRooms = "SELECT * FROM Rooms";
        ResultSet rs = DBManager.dbExecuteQuery(queryRooms);
        ObservableList<Rooms> roomsObservableList = FXCollections.observableArrayList();
        while (rs.next()){
            Rooms rooms = new Rooms();
            rooms.setId(rs.getInt("id"));
            rooms.setCategory(rs.getString("category"));
            roomsObservableList.add(rooms);
        }
        return roomsObservableList;
    }





}
