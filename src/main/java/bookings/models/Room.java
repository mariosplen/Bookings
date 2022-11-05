package bookings.models;

public record Room(
        int id,
        String category,
        Boolean isClean,
        Boolean isAvailable
) {
}
