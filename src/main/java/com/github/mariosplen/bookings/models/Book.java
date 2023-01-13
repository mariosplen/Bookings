package com.github.mariosplen.bookings.models;

import java.time.LocalDate;

public record Book(
        int id,
        int roomId,
        String roomCategory,
        String guestName,
        LocalDate checkIn,
        LocalDate checkOut,
        LocalDate date,
        int totalPrice
) {
}
