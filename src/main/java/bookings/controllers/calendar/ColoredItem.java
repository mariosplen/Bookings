package bookings.controllers.calendar;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Paint;

public record ColoredItem(
        StringProperty value,
        ObjectProperty<Paint> background
) {
    public ColoredItem(String value, Paint paint) {
        this(new SimpleStringProperty(value), new SimpleObjectProperty<>(paint));
    }
}