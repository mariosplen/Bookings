package bookings.util;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Paint;

public class ColoredItem {

    private final StringProperty value;
    private final ObjectProperty<Paint> background;

    public ColoredItem(String value, Paint background) {
        this.background = new SimpleObjectProperty<>(background);
        this.value = new SimpleStringProperty(value);
    }

    public ColoredItem(String value) {
        this(value, null);
    }

    public final Paint getBackground() {
        return this.background.get();
    }

    public final void setBackground(Paint value) {
        this.background.set(value);
    }

    public final ObjectProperty<Paint> backgroundProperty() {
        return this.background;
    }

    public final String getValue() {
        return this.value.get();
    }

    public final void setValue(String value) {
        this.value.set(value);
    }

    public final StringProperty valueProperty() {
        return this.value;
    }
}