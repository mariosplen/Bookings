package bookings.models;

public record User(
        int id,
        String username,
        String password,
        Boolean canDoBasic,
        Boolean canViewInfo,
        Boolean canManageGuests,
        Boolean canManageUsers,
        String image
) {
}
