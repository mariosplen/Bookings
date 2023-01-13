package com.github.mariosplen.bookings.controllers.calendar;

import javafx.scene.shape.Rectangle;

public class ColoredItem extends Rectangle {


    public ColoredItem() {
        setWidth(127);
        setHeight(30);
        getStyleClass().add("calendarCell");

    }

}