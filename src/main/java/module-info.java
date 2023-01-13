module com.github.mariosplen.bookings {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    requires java.sql.rowset;
    requires org.controlsfx.controls;
    requires java.desktop;
    requires org.apache.pdfbox;
    requires org.xerial.sqlitejdbc;

    exports com.github.mariosplen.bookings.controllers;
    exports com.github.mariosplen.bookings;
    exports com.github.mariosplen.bookings.models;
    exports com.github.mariosplen.bookings.util;
    exports com.github.mariosplen.bookings.controllers.books;
    exports com.github.mariosplen.bookings.controllers.calendar;
    exports com.github.mariosplen.bookings.controllers.home;
    exports com.github.mariosplen.bookings.controllers.register;
    exports com.github.mariosplen.bookings.controllers.recovery;
    exports com.github.mariosplen.bookings.controllers.users;
    exports com.github.mariosplen.bookings.controllers.loading;
    exports com.github.mariosplen.bookings.controllers.reservation;
    exports com.github.mariosplen.bookings.controllers.guests;
    exports com.github.mariosplen.bookings.controllers.new_guest;

    opens com.github.mariosplen.bookings.controllers to javafx.fxml;
    opens com.github.mariosplen.bookings to javafx.fxml;
    opens com.github.mariosplen.bookings.models to javafx.fxml;
    opens com.github.mariosplen.bookings.util to javafx.fxml;
    opens com.github.mariosplen.bookings.controllers.books to javafx.fxml;
    opens com.github.mariosplen.bookings.controllers.calendar to javafx.fxml;
    opens com.github.mariosplen.bookings.controllers.recovery to javafx.fxml;
    opens com.github.mariosplen.bookings.controllers.home to javafx.fxml;
    opens com.github.mariosplen.bookings.controllers.new_guest to javafx.fxml;
    opens com.github.mariosplen.bookings.controllers.login to javafx.fxml;
    opens com.github.mariosplen.bookings.controllers.register to javafx.fxml;
    opens com.github.mariosplen.bookings.controllers.users to javafx.fxml;
    opens com.github.mariosplen.bookings.controllers.loading to javafx.fxml;
    opens com.github.mariosplen.bookings.controllers.reservation to javafx.fxml;
    opens com.github.mariosplen.bookings.controllers.guests to javafx.fxml;
}