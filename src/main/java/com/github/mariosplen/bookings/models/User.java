package com.github.mariosplen.bookings.models;

import java.util.Objects;

public final class User {

    private final String username;
    private final String password;
    private final Boolean canDoBasic;
    private final Boolean canManageGuests;
    private final Boolean canManageUsers;

    User(
            String username,
            String password,
            Boolean canDoBasic,
            Boolean canManageGuests,
            Boolean canManageUsers
    ) {
        this.username = username;
        this.password = password;
        this.canDoBasic = canDoBasic;
        this.canManageGuests = canManageGuests;
        this.canManageUsers = canManageUsers;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public Boolean canDoBasic() {
        return canDoBasic;
    }

    public Boolean canManageGuests() {
        return canManageGuests;
    }

    public Boolean canManageUsers() {
        return canManageUsers;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        User that = (User) obj;
        return Objects.equals(this.username, that.username)
                && Objects.equals(this.password, that.password)
                && Objects.equals(this.canDoBasic, that.canDoBasic)
                && Objects.equals(this.canManageGuests, that.canManageGuests)
                && Objects.equals(this.canManageUsers, that.canManageUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, canDoBasic, canManageGuests, canManageUsers);
    }

    @Override
    public String toString() {
        return "User["
                + "username=" + username + ", "
                + "password=" + password + ", "
                + "canDoBasic=" + canDoBasic + ", "
                + "canManageGuests=" + canManageGuests + ", "
                + "canManageUsers=" + canManageUsers + ']';
    }

}
