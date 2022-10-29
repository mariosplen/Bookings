package bookings.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Room {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty category;
    private final BooleanProperty isClean;
    private final BooleanProperty isAvailable;

    public Room() {
        this.id = new SimpleIntegerProperty();
        this.category = new SimpleStringProperty();
        this.isClean = new SimpleBooleanProperty();
        this.isAvailable = new SimpleBooleanProperty();
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public boolean isIsClean() {
        return isClean.get();
    }

    public void setIsClean(boolean isClean) {
        this.isClean.set(isClean);
    }

    public BooleanProperty isCleanProperty() {
        return isClean;
    }

    public boolean isIsAvailable() {
        return isAvailable.get();
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable.set(isAvailable);
    }

    public BooleanProperty isAvailableProperty() {
        return isAvailable;
    }
}
