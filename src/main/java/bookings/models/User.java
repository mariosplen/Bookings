package bookings.models;

public record User(
        int id,
        String username,
        String password,
        int privLevel
) {
}
