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
        Boolean isChargedFromPhonePrepayment,
        String paymentMethod,
        Boolean isSummerCharged,
        Boolean isChristmasCharged,
        Boolean isEasterCharged,
        Boolean isEventCharged,
        Boolean isUsingLowOccupancyOffer,
        int doorPrice, // EG: $1.00 is stored as 100
        int totalPrice,
        String cardNumber,
        String cardVCC

) {
}
