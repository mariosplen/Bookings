package bookings.models;

import java.time.LocalDate;

public record Book(
        int id,
        int roomId,
        int guestId,
        LocalDate checkIn,
        LocalDate checkOut,
        String status,
        LocalDate date,
        Boolean isUsingPrepaymentOffer,
        Boolean isChargedPhonePrepayment,
        String paymentMethod,
        String cardNumber,
        String cardVCC,
        int totalPrice
        // EG: $1.00 is stored as 100
) {
}
