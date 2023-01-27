package com.github.mariosplen.bookings.models;

import java.util.Objects;

public final class Room {

    private final int id;
    private final String category;

    Room(
            int id,
            String category
    ) {
        this.id = id;
        this.category = category;
    }

    public int id() {
        return id;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Room that = (Room) obj;
        return this.id == that.id
                && Objects.equals(this.category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category);
    }

    @Override
    public String toString() {
        return "Room["
                + "id=" + id + ", "
                + "category=" + category + ']';
    }

}
