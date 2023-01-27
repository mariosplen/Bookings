package com.github.mariosplen.bookings.models;

import java.util.Objects;

public final class Guest {

    private final String name;
    private final String phone;
    private final String email;

    Guest(
            String name,
            String phone,
            String email
    ) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String name() {
        return name;
    }

    public String phone() {
        return phone;
    }

    public String email() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Guest that = (Guest) obj;
        return Objects.equals(this.name, that.name)
                && Objects.equals(this.phone, that.phone)
                && Objects.equals(this.email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email);
    }

    @Override
    public String toString() {
        return "Guest["
                + "name=" + name + ", "
                + "phone=" + phone + ", "
                + "email=" + email + ']';
    }

}
