module com.github.mariosplen.bookings {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.jfxtras.styles.jmetro;
    requires java.sql.rowset;

    exports bookings.controllers;
    exports bookings.application;
    exports bookings.models;
    exports bookings.util;

    opens bookings.controllers to javafx.fxml;
    opens bookings.application to javafx.fxml;
    opens bookings.models to javafx.fxml;
    opens bookings.util to javafx.fxml;
}