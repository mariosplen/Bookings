package bookings.models;


import bookings.util.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestDAO {

    public static ObservableList<Guest> getGuests() throws SQLException, ClassNotFoundException {
        String queryGuests = "SELECT * FROM guests";
        ResultSet rs = DBManager.dbExecuteQuery(queryGuests);

        return getGuestsFromRs(rs);
    }


    public static Guest getGuestFromRs(ResultSet rs) throws SQLException {
        Guest guest = new Guest();
        guest.setId(rs.getInt("id"));
        guest.setName(rs.getString("name"));
        guest.setPhone(rs.getString("phone"));
        guest.setEmail(rs.getString("email"));
        guest.setIsGroup(rs.getBoolean("group"));

        return guest;
    }

    public static ObservableList<Guest> getGuestsFromRs(ResultSet rs) throws SQLException {
        ObservableList<Guest> guestsList = FXCollections.observableArrayList();
        while (rs.next()) {
            guestsList.add(getGuestFromRs(rs));
        }

        return guestsList;
    }


}
