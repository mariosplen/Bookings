package bookings.models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {

    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty roomId;
    private final SimpleIntegerProperty guestId;
    private final SimpleIntegerProperty checkIn;
    private final SimpleIntegerProperty checkOut;
    private final SimpleStringProperty status;
    private final SimpleIntegerProperty date;
    private final SimpleBooleanProperty isUsingPrepaymentOffer;
    private final SimpleBooleanProperty isChargedPhonePrepayment;
    private final SimpleStringProperty paymentMethod;
    private final SimpleStringProperty cardNumber;
    private final SimpleStringProperty cardVCC;
    private final SimpleDoubleProperty totalPrice;


    public Book() {
        this.id = new SimpleIntegerProperty();
        this.roomId = new SimpleIntegerProperty();
        this.guestId = new SimpleIntegerProperty();
        this.checkIn = new SimpleIntegerProperty();
        this.checkOut = new SimpleIntegerProperty();
        this.status = new SimpleStringProperty();
        this.date = new SimpleIntegerProperty();
        this.isUsingPrepaymentOffer = new SimpleBooleanProperty();
        this.isChargedPhonePrepayment = new SimpleBooleanProperty();
        this.paymentMethod = new SimpleStringProperty();
        this.cardNumber = new SimpleStringProperty();
        this.cardVCC = new SimpleStringProperty();
        this.totalPrice = new SimpleDoubleProperty();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public int getRoomId() {
        return roomId.get();
    }

    public void setRoomId(int roomId) {
        this.roomId.set(roomId);
    }

    public SimpleIntegerProperty roomIdProperty() {
        return roomId;
    }

    public int getGuestId() {
        return guestId.get();
    }

    public void setGuestId(int guestId) {
        this.guestId.set(guestId);
    }

    public SimpleIntegerProperty guestIdProperty() {
        return guestId;
    }

    public int getCheckIn() {
        return checkIn.get();
    }

    public void setCheckIn(int checkIn) {
        this.checkIn.set(checkIn);
    }

    public SimpleIntegerProperty checkInProperty() {
        return checkIn;
    }

    public int getCheckOut() {
        return checkOut.get();
    }

    public void setCheckOut(int checkOut) {
        this.checkOut.set(checkOut);
    }

    public SimpleIntegerProperty checkOutProperty() {
        return checkOut;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public int getDate() {
        return date.get();
    }

    public void setDate(int date) {
        this.date.set(date);
    }

    public SimpleIntegerProperty dateProperty() {
        return date;
    }

    public boolean isIsUsingPrepaymentOffer() {
        return isUsingPrepaymentOffer.get();
    }

    public void setIsUsingPrepaymentOffer(boolean isUsingPrepaymentOffer) {
        this.isUsingPrepaymentOffer.set(isUsingPrepaymentOffer);
    }

    public SimpleBooleanProperty isUsingPrepaymentOfferProperty() {
        return isUsingPrepaymentOffer;
    }

    public boolean isIsChargedPhonePrepayment() {
        return isChargedPhonePrepayment.get();
    }

    public void setIsChargedPhonePrepayment(boolean isChargedPhonePrepayment) {
        this.isChargedPhonePrepayment.set(isChargedPhonePrepayment);
    }

    public SimpleBooleanProperty isChargedPhonePrepaymentProperty() {
        return isChargedPhonePrepayment;
    }

    public String getPaymentMethod() {
        return paymentMethod.get();
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod.set(paymentMethod);
    }

    public SimpleStringProperty paymentMethodProperty() {
        return paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber.get();
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber.set(cardNumber);
    }

    public SimpleStringProperty cardNumberProperty() {
        return cardNumber;
    }

    public String getCardVCC() {
        return cardVCC.get();
    }

    public void setCardVCC(String cardVCC) {
        this.cardVCC.set(cardVCC);
    }

    public SimpleStringProperty cardVCCProperty() {
        return cardVCC;
    }

    public double getTotalPrice() {
        return totalPrice.get();
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice.set(totalPrice);
    }

    public SimpleDoubleProperty totalPriceProperty() {
        return totalPrice;
    }
}
