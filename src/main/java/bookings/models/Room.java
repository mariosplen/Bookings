package bookings.models;

public record Room(
        int id,
        String category,
        String status,
        Boolean isAvailable
) {
}
