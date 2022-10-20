module com.github.mariosplen.bookings {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.jfxtras.styles.jmetro;
    requires java.sql.rowset;

    exports bookings.controllers;
    exports bookings.application;

    opens bookings.controllers to javafx.fxml;
}