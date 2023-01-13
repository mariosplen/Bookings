package com.github.mariosplen.bookings.controllers.books;


import com.github.mariosplen.bookings.models.Book;
import com.github.mariosplen.bookings.models.BookDAO;
import com.github.mariosplen.bookings.models.Guest;
import com.github.mariosplen.bookings.models.GuestDAO;
import com.github.mariosplen.bookings.util.ReceiptGenerator;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class BooksView implements Initializable {


    @FXML
    ObservableList<Book> books;
    @FXML
    private TableView<Book> bookTableView;
    @FXML
    private TableColumn<Book, Button>
            saveButtonColumn,
            deleteButtonColumn;
    @FXML
    private TableColumn<Book, Number>
            room_id,
            nights,
            price;
    @FXML
    private TableColumn<Book, String>
            check_in,
            check_out,
            guest_name,
            room_category,
            date;


    private void tableViewValues() throws SQLException, ClassNotFoundException {
        ObservableList<Book> roomObservableList = BookDAO.getBooks();
        saveButtonColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(new Button("Save")));
        saveButtonColumn.setCellFactory(param -> new TableCell<>() {
            final Button button = new Button("Save");

            @Override
            protected void updateItem(Button item, boolean empty) {
                button.getStyleClass().add("verySmallBtn");
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    button.setOnAction(event -> {
                        Book book = getTableView().getItems().get(getIndex());
                        try {
                            ReceiptGenerator receiptGenerator = new ReceiptGenerator();
                            receiptGenerator.genReceipt(GuestDAO.getGuests().stream()
                                            .map(Guest::name)
                                            .filter(name -> Objects.equals(name, book.guestName()))
                                            .findFirst()
                                            .orElse(null),
                                    book.checkIn(),
                                    book.checkOut(),
                                    book.totalPrice()
                            );
                        } catch (IOException | SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    setGraphic(button);
                }
            }
        });
        deleteButtonColumn.setCellFactory(param -> new TableCell<>() {
            final Button button = new Button("Delete");


            @Override
            protected void updateItem(Button item, boolean empty) {
                button.getStyleClass().add("verySmallRedBtn");
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    button.setOnAction(event -> {
                        Book book = getTableView().getItems().get(getIndex());
                        try {

                            // Delete the book from the database using the BookDAO class
                            BookDAO.deleteBook(book.id());
                            // Remove the book from the table's data
                            books.remove(book);
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            tableViewValues();
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    setGraphic(button);
                }
            }
        });

        guest_name.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().guestName()));
        room_id.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().roomId()));
        check_in.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().checkIn().toString()));
        check_out.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().checkOut().toString()));
        date.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().date().toString()));
        room_category.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().roomCategory()));
        price.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().totalPrice()));
        nights.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().checkIn().until(p.getValue().checkOut()).getDays()));

        bookTableView.setItems(roomObservableList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            books = BookDAO.getBooks();
            tableViewValues();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
