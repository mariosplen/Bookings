package com.github.mariosplen.bookings.models;

public record User(
        String username,
        String password,
        Boolean canDoBasic,
        Boolean canManageGuests,
        Boolean canManageUsers
) {
}