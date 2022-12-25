package bookings.controllers.calendar;

import bookings.models.BookDAO;
import bookings.models.Room;
import bookings.models.RoomDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class CalendarView implements Initializable {
    Map<Integer, List<LocalDate>> roomBookDates;
    @FXML
    private GridPane gridPane;
    @FXML
    private DatePicker fromDP;
    @FXML
    private DatePicker toDP;
    private LocalDate fromDate;
    private List<Room> rooms;

    // Initializes the calendar view when it is loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get rooms from database
        try {
            rooms = RoomDAO.getRooms();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Set default dates for the from and to date pickers
        fromDP.setValue(YearMonth.now().atDay(1));
        toDP.setValue(YearMonth.now().atEndOfMonth());


        setCalendarData();

    }

    @FXML
    private void setCalendarData() {

        gridPane.getChildren().clear();

        // Get values from the datePickers
        fromDate = fromDP.getValue();
        LocalDate toDate = toDP.getValue();

        // Get a list of all the dates between the start and end dates
        List<LocalDate> allDates = new ArrayList<>(fromDate.datesUntil(toDate).toList());

        // Set the calendar tag
        Label roomTag = new Label("Δωμάτιο");
        roomTag.getStyleClass().add("calendarCellTagWhite");
        roomTag.setPadding(new Insets(2));
        gridPane.add(roomTag, 0, 0);

        // Set the rooms in the calendar grid
        for (int i = 0; i < rooms.size(); i++) {
            Label roomIdText = new Label(String.valueOf(rooms.get(i).id()));
            roomIdText.getStyleClass().add("calendarCellTagBlack");
            BorderPane centered = new BorderPane();
            centered.setCenter(roomIdText);
            gridPane.addRow(i + 1, centered);
        }


        // Set the dates in the calendar grid
        for (int i = 0; i < allDates.size(); i++) {
            Label dateText = new Label(allDates.get(i).toString());
            dateText.getStyleClass().add("calendarCellTagWhite");
            dateText.setPadding(new Insets(2, 8, 2, 8));
            BorderPane centered = new BorderPane();
            centered.setCenter(dateText);
            gridPane.addColumn(i + 1, centered);
        }


        // Get a map of room bookings, with the room id as the key and a list of booked dates as the value
        try {
            roomBookDates = BookDAO.getRoomBookDates();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Fill gridPane with available ColoredItems
        for (int r = 0; r < rooms.size(); r++) {
            for (int c = 0; c < allDates.size(); c++) {
                ColoredItem box = new ColoredItem();
                BorderPane centered = new BorderPane();
                centered.setCenter(box);
                gridPane.add(centered, c + 1, r + 1);
            }
        }


        roomBookDates.forEach((integer, localDates) -> {
            for (int i = 0; i < rooms.size(); i++) {
                if (String.valueOf(rooms.get(i).id()).equals(String.valueOf(integer))) {
                    for (LocalDate date : localDates) {
                        for (int j = 0; j < allDates.size(); j++) {
                            if (allDates.get(j).equals(date)) {
                                int finalI = i;
                                int finalJ = j;
                                gridPane.getChildren().forEach(node -> {
                                    if (GridPane.getColumnIndex(node) == (finalJ + 1) && GridPane.getRowIndex(node) == (finalI + 1)) {
                                        node.setDisable(true);
                                        Tooltip.install(node, new Tooltip("Book_id = " + integer));
                                    }
                                });
                            }
                        }

                    }
                }
            }

        });
    }

    public void onNextMonthClk() {
        LocalDate initial = fromDate.plusMonths(1);

        LocalDate nextStartOfMonth = initial.withDayOfMonth(1);
        LocalDate nextEndOfMonth =
                initial.withDayOfMonth(initial.getMonth().length(initial.isLeapYear()));

        fromDP.setValue(nextStartOfMonth);
        toDP.setValue(nextEndOfMonth);
        setCalendarData();
    }

    public void onPrevMonthClk() {

        LocalDate initial = fromDate.minusMonths(1);

        LocalDate prevStartOfMonth = initial.withDayOfMonth(1);
        LocalDate prevEndOfMonth =
                initial.withDayOfMonth(initial.getMonth().length(initial.isLeapYear()));

        fromDP.setValue(prevStartOfMonth);
        toDP.setValue(prevEndOfMonth);
        setCalendarData();
    }
}
