package bookings.controllers;

import bookings.models.BookDAO;
import bookings.models.Room;
import bookings.models.RoomDAO;
import bookings.util.ColoredItem;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import java.util.*;

public class CalendarController implements Initializable {
    public TableView calendarTV;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    public void onSearchClicked() throws SQLException, ClassNotFoundException {
        List<String> columns = new ArrayList<>(Arrays.asList("RoomId", "Category"));

        ObservableList<Room> rooms = RoomDAO.getRooms();
        Map<Integer, List<LocalDate>> roomBookDates = BookDAO.getRoomBookDates();

        LocalDate fromDate = LocalDate.parse("2022-10-30");
        LocalDate toDate = LocalDate.parse("2022-11-11");

        List<LocalDate> dates = new ArrayList<>(fromDate.datesUntil(toDate).toList());
        dates.add(toDate);

        dates.forEach(date -> {
            columns.add(date.toString());
        });


        for (int i = 0; i < columns.size(); i++) {
            final int finalIdx = i;
            TableColumn<ObservableList<ColoredItem>, ColoredItem> column = new TableColumn<>(columns.get(i));
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
                        textProperty().bind(item.valueProperty());
                        backgroundProperty().bind(Bindings.createObjectBinding(() -> new Background(new BackgroundFill(item.getBackground(), CornerRadii.EMPTY, Insets.EMPTY)), item.backgroundProperty()));
                    }
                }

            });
            calendarTV.getColumns().add(column);
        }


        rooms.forEach(room -> {
            ObservableList<ColoredItem> rowItems = FXCollections.observableArrayList();
            final int[] columnIdx = {0};
            calendarTV.getColumns().forEach(column -> {
                if (columnIdx[0] < 2) {
                    if (columnIdx[0] == 0) {
                        rowItems.add(new ColoredItem(String.valueOf(room.id())));
                    } else {
                        rowItems.add(new ColoredItem(room.category()));
                    }
                    columnIdx[0]++;
                } else {
                    if (roomBookDates.containsKey(room.id())) {
                        if (roomBookDates.get(room.id()).contains(LocalDate.parse(((TableColumn<ObservableList<ColoredItem>, ColoredItem>) column).getText()))) {
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
}
