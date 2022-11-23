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
        String query = "SELECT * FROM books;";
        ResultSet rs = DBManager.dbExecuteQuery(query);
        ObservableList<Book> books = getBooksFromRS(rs);
        rs.close();

        return books;
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
                rs.getBoolean("from_phone_prepayment"),
                rs.getString("payment_method"),
                rs.getBoolean("summer_charge"),
                rs.getBoolean("christmas_charge"),
                rs.getBoolean("easter_charge"),
                rs.getBoolean("event_charge"),
                rs.getBoolean("low_occupancy_offer"),
                rs.getInt("door_price"),
                rs.getInt("total_price"),
                rs.getString("card_number"),
                rs.getString("card_vcc")
        );

    }

    public static ObservableList<Book> getBooksFromRS(ResultSet rs) throws SQLException {
        ObservableList<Book> books = FXCollections.observableArrayList();
        while (rs.next()) {
            books.add(getBookFromRs(rs));
        }
        return books;
    }

    public static Map<Integer, List<LocalDate>> getRoomBookDates() throws SQLException, ClassNotFoundException {
        String query = "SELECT room_id, check_in, check_out FROM books;";

        ResultSet rs = DBManager.dbExecuteQuery(query);

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
        rs.close();
        return roomBookDates;
    }

    public static void deleteBook(int id) throws SQLException, ClassNotFoundException {
        String query = """
                DELETE FROM books
                WHERE  id = ?;
                """;
        DBManager.dbExecuteUpdate(query, id);
    }

    public static void addBook(
            int roomId,
            int guestId,
            LocalDate checkIn,
            LocalDate checkOut,
            String status,
            LocalDate date,
            Boolean isUsingPrepaymentOffer,
            Boolean isChargedFromPhonePrepayment,
            String paymentMethod,
            Boolean isSummerCharged,
            Boolean isChristmasCharged,
            Boolean isEasterCharged,
            Boolean isEventCharged,
            Boolean isUsingLowOccupancyOffer,
            int doorPrice,
            int totalPrice,
            String cardNumber,
            String cardVCC
    ) throws SQLException, ClassNotFoundException {
        String query = """
                INSERT INTO books(room_id, guest_id, check_in, check_out, status, date, prepayment_offer, from_phone_prepayment, payment_method, summer_charge, christmas_charge, easter_charge, event_charge, low_occupancy_offer, door_price, total_price, card_number, card_vcc)
                VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        DBManager.dbExecuteUpdate(query,
                roomId,
                guestId,
                checkIn,
                checkOut,
                status,
                date,
                isUsingPrepaymentOffer,
                isChargedFromPhonePrepayment,
                paymentMethod,
                isSummerCharged,
                isChristmasCharged,
                isEasterCharged,
                isEventCharged,
                isUsingLowOccupancyOffer,
                doorPrice,
                totalPrice,
                cardNumber,
                cardVCC
        );

    }


    public static int getOccupancyPercentage(LocalDate checkIn) throws SQLException, ClassNotFoundException {
        int percentage = 0;
        String query = """
                SELECT CAST((SELECT Count(check_in)
                             FROM   books
                             WHERE  check_in = ?) / (SELECT Cast(Count(id) AS REAL)
                                                                FROM   rooms) * 100 AS INTEGER)
                       AS
                       percentage;
                """;
        ResultSet rs = DBManager.dbExecuteQuery(query, checkIn);
        if(rs.next()){
            percentage = rs.getInt(1);
        }
        rs.close();

        return percentage;
    }

}

