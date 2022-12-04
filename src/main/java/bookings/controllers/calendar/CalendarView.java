package bookings.controllers.calendar;

import bookings.models.BookDAO;
import bookings.models.Room;
import bookings.models.RoomDAO;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class CalendarView implements Initializable {
    @FXML
    private TableView<ObservableList<ColoredItem>> calendarTV;
    @FXML
    private DatePicker fromDP;
    @FXML
    private DatePicker toDP;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate startOfMonth = YearMonth.now().atDay(1);
        LocalDate endOfMonth = YearMonth.now().atEndOfMonth();

        fromDP.setValue(startOfMonth);
        toDP.setValue(endOfMonth);
        try {
            onSearchClicked();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onSearchClicked() throws SQLException, ClassNotFoundException {
        calendarTV.getItems().clear();
        calendarTV.getColumns().clear();

        List<String> columns = new ArrayList<>(Arrays.asList("RoomId", "Category"));

        ObservableList<Room> rooms = RoomDAO.getRooms();
        Map<Integer, List<LocalDate>> roomBookDates = BookDAO.getRoomBookDates();

        LocalDate fromDate = fromDP.getValue();
        LocalDate toDate = toDP.getValue();

        List<LocalDate> dates = new ArrayList<>(fromDate.datesUntil(toDate).toList());
        dates.add(toDate);

        dates.forEach(date -> columns.add(date.toString()));


        for (int i = 0; i < columns.size(); i++) {
            final int finalIdx = i;
            TableColumn<ObservableList<ColoredItem>, ColoredItem> column = new TableColumn<>(columns.get(i));
            column.setSortable(false);
            column.setCellValueFactory(param -> Bindings.valueAt(param.getValue(), finalIdx));
            column.setCellFactory(tv -> new TableCell<>() {

                @Override
                protected void updateItem(ColoredItem item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        // remove background and text
                        backgroundProperty().unbind();
                        textProperty().unbind();
                        setBackground(Background.EMPTY);
                        setText(null);
                    } else {
                        // bind background and text to the item properties
                        textProperty().bind(item.value());
                        backgroundProperty().bind(Bindings.createObjectBinding(() -> new Background(new BackgroundFill(
                                item.background().getValue(),
                                CornerRadii.EMPTY,
                                Insets.EMPTY)), item.background()));
                    }
                }

            });
            calendarTV.getColumns().add(column);
        }


        rooms.forEach(room -> {
            ObservableList<ColoredItem> rowItems = FXCollections.observableArrayList();


            int[] idx = {0};
            calendarTV.getColumns().forEach(column -> {

                if (idx[0] < 2) {
                    if (idx[0] == 0) {
                        rowItems.add(new ColoredItem(String.valueOf(room.id()), Color.WHITE));
                    } else {
                        rowItems.add(new ColoredItem(room.category(), Color.WHITE));
                    }
                    idx[0]++;
                } else {
                    if (roomBookDates.containsKey(room.id())) {
                        if (roomBookDates.get(room.id()).contains(LocalDate.parse(column.getText()))) {
                            rowItems.add(new ColoredItem("", Color.RED));
                        } else {
                            rowItems.add(new ColoredItem("", Color.GREEN));
                        }
                    } else {
                        rowItems.add(new ColoredItem("", Color.GREEN));
                    }
                }
            });
            calendarTV.getItems().add(rowItems);
        });

    }

    public void onNextMonthClk() throws SQLException, ClassNotFoundException {
        LocalDate initial = fromDP.getValue().plusMonths(1);

        LocalDate nextStartOfMonth = initial.withDayOfMonth(1);
        LocalDate nextEndOfMonth =
                initial.withDayOfMonth(initial.getMonth().length(initial.isLeapYear()));

        fromDP.setValue(nextStartOfMonth);
        toDP.setValue(nextEndOfMonth);
        onSearchClicked();
    }

    public void onPrevMonthClk() throws SQLException, ClassNotFoundException {

        LocalDate initial = fromDP.getValue().minusMonths(1);

        LocalDate prevStartOfMonth = initial.withDayOfMonth(1);
        LocalDate prevEndOfMonth =
                initial.withDayOfMonth(initial.getMonth().length(initial.isLeapYear()));

        fromDP.setValue(prevStartOfMonth);
        toDP.setValue(prevEndOfMonth);
        onSearchClicked();
    }
}
