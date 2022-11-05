package bookings.models;

import bookings.util.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDAO {

    public static ObservableList<Book> getBooks() throws SQLException, ClassNotFoundException {
        String queryBooks = "SELECT * FROM books";
        ResultSet rs = DBManager.dbExecuteQuery(queryBooks);

        return getBooksFromRS(rs);
    }


    public static Book getBookFromRs(ResultSet rs) throws SQLException {


        return new Book(rs.getInt("id"),
                        rs.getInt("room_id"),
                        rs.getInt("guest_id"),
                        LocalDate.parse(rs.getString("check_in")),
                        LocalDate.parse(rs.getString("check_out")),
                        rs.getString("status"),
                        LocalDate.parse(rs.getString("date")),
                        rs.getBoolean("prepayment_offer"),
                        rs.getBoolean("prepayment_phone_charge"),
                        rs.getString("payment_method"),
                        rs.getString("card_number"),
                        rs.getString("card_vcc"),
                        rs.getInt("total_price"));
    }

    public static ObservableList<Book> getBooksFromRS(ResultSet rs) throws SQLException {
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        while (rs.next()) {
            bookList.add(getBookFromRs(rs));
        }
        return bookList;
    }

    public static Map<Integer, List<LocalDate>> getRoomBookDates() throws SQLException, ClassNotFoundException {
        String queryBooks = "SELECT room_id, check_in, check_out FROM books ";

        ResultSet rs = DBManager.dbExecuteQuery(queryBooks);


        Map<Integer, List<LocalDate>> roomBookDates = new HashMap<>();
        while (rs.next()) {
            LocalDate from = LocalDate.parse(rs.getString("check_in"));
            LocalDate to = LocalDate.parse(rs.getString("check_out"));
            List<LocalDate> dates = new ArrayList<>(from.datesUntil(to).toList());
            dates.add(to);


            if (roomBookDates.containsKey(rs.getInt("room_id"))) {
                roomBookDates.get(rs.getInt("room_id")).addAll(dates);
            } else {
                roomBookDates.put(rs.getInt("room_id"), dates);
            }


        }
        return roomBookDates;
    }
}
