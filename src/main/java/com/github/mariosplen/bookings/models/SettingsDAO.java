package com.github.mariosplen.bookings.models;

import com.github.mariosplen.bookings.util.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingsDAO {

    public static String getHotelKey() throws SQLException, ClassNotFoundException {
        String query = "SELECT value FROM settings WHERE name = 'hotel_key'";
        ResultSet rs = DBManager.dbExecuteQuery(query);

        String key = getKeyFromRS(rs);
        rs.close();
        return key;
    }

    private static String getKeyFromRS(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return rs.getString("value");
        }
        return null;
    }
}
