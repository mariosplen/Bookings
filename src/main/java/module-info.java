module com.github.mariosplen.bookings {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    requires java.sql.rowset;
    requires org.xerial.sqlitejdbc;


    exports bookings.controllers;
    exports bookings.application;
    exports bookings.models;
    exports bookings.util;
    exports bookings.controllers.books;
    exports bookings.controllers.calendar;
    exports bookings.controllers.home;
    exports bookings.controllers.rooms;
    exports bookings.controllers.register;
    exports bookings.controllers.guests;
    exports bookings.controllers.loading;

    opens bookings.controllers to javafx.fxml;
    opens bookings.application to javafx.fxml;
    opens bookings.models to javafx.fxml;
    opens bookings.util to javafx.fxml;
    opens bookings.controllers.books to javafx.fxml;
    opens bookings.controllers.calendar to javafx.fxml;
    opens bookings.controllers.home to javafx.fxml;
    opens bookings.controllers.login to javafx.fxml;
    opens bookings.controllers.register to javafx.fxml;
    opens bookings.controllers.rooms to javafx.fxml;
    opens bookings.controllers.guests to javafx.fxml;
    opens bookings.controllers.loading to javafx.fxml;

}