package com.github.mariosplen.bookings.models;

import com.github.mariosplen.bookings.util.DBManager;
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


    public static void addBook(
            int roomId,
            String roomCategory,
            String guestName,
            LocalDate checkIn,
            LocalDate checkOut,
            LocalDate date,
            int totalPrice
    ) throws SQLException, ClassNotFoundException {
        String query = """
                INSERT INTO books(room_id, room_category, guest_name, check_in, check_out,  date, total_price)
                VALUES(?, ?, ?, ?, ?, ?, ?)
                """;
        DBManager.dbExecuteUpdate(query,
                roomId,
                roomCategory,
                guestName,
                checkIn,
                checkOut,
                date,
                totalPrice
        );

    }

    public static ObservableList<Book> getBooks() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM books;";
        ResultSet rs = DBManager.dbExecuteQuery(query);
        ObservableList<Book> books = getBooksFromRS(rs);
        rs.close();

        return books;
    }

    public static Book getBookFromRs(ResultSet rs) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getInt("room_id"),
                rs.getString("room_category"),
                rs.getString("guest_name"),
                LocalDate.parse(rs.getString("check_in")),
                LocalDate.parse(rs.getString("check_out")),
                LocalDate.parse(rs.getString("date")),
                rs.getInt("total_price")
        );

    }

    public static ObservableList<Book> getBooksFromRS(ResultSet rs) throws SQLException {
        ObservableList<Book> books = FXCollections.observableArrayList();
        while (rs.next()) {
            books.add(getBookFromRs(rs));
        }
        return books;
    }

    public static void deleteBook(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM books WHERE id = ? ";
        DBManager.dbExecuteUpdate(query, id);

    }
}

