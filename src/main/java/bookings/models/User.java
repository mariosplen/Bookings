package bookings.models;

public record User(
        int id,
        String username,
        String password,
        Boolean canDoBasicActions,
        Boolean canViewStatistics,
        Boolean canViewRevenue,
        Boolean canManageUsers
) {
}
