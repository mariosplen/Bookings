module com.github.mariosplen.bookings {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens com.github.mariosplen.bookings to javafx.fxml;
    exports com.github.mariosplen.bookings;
}