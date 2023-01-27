package com.github.mariosplen.bookings.models;

import java.time.LocalDate;
import java.util.Objects;

public final class Book {

    private final int id;
    private final int roomId;
    private final String roomCategory;
    private final String guestName;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final LocalDate date;
    private final int totalPrice;

    public Book(
            int id,
            int roomId,
            String roomCategory,
            String guestName,
            LocalDate checkIn,
            LocalDate checkOut,
            LocalDate date,
            int totalPrice
    ) {
        this.id = id;
        this.roomId = roomId;
        this.roomCategory = roomCategory;
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public int id() {
        return id;
    }

    public int roomId() {
        return roomId;
    }

    public String roomCategory() {
        return roomCategory;
    }

    public String guestName() {
        return guestName;
    }

    public LocalDate checkIn() {
        return checkIn;
    }

    public LocalDate checkOut() {
        return checkOut;
    }

    public LocalDate date() {
        return date;
    }

    public int totalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Book that = (Book) obj;
        return this.id == that.id
                && this.roomId == that.roomId
                && Objects.equals(this.roomCategory, that.roomCategory)
                && Objects.equals(this.guestName, that.guestName)
                && Objects.equals(this.checkIn, that.checkIn)
                && Objects.equals(this.checkOut, that.checkOut)
                && Objects.equals(this.date, that.date)
                && this.totalPrice == that.totalPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomId, roomCategory, guestName, checkIn, checkOut, date, totalPrice);
    }

    @Override
    public String toString() {
        return "Book["
                + "id=" + id + ", "
                + "roomId=" + roomId + ", "
                + "roomCategory=" + roomCategory + ", "
                + "guestName=" + guestName + ", "
                + "checkIn=" + checkIn + ", "
                + "checkOut=" + checkOut + ", "
                + "date=" + date + ", "
                + "totalPrice=" + totalPrice + ']';
    }

}
