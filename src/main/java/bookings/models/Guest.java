package bookings.models;

public record Guest(
        int id,
        String name,
        String phone,
        String email,
        Boolean isGroup
) {
}
