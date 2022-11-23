package bookings.controllers.dashboard;

import bookings.models.BookDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FormController {


    @FXML
    private Label percentage;
    int totalPrice;
    @FXML
    private CheckBox summerCB;
    @FXML
    private CheckBox christmasCB;
    @FXML
    private CheckBox easterCB;
    @FXML
    private CheckBox percentageCB;
    @FXML
    private CheckBox eventCB;
    @FXML
    private TextField priceTF;
    @FXML
    private TextField vccTF;
    @FXML
    private TextField cardTF;
    @FXML
    private ChoiceBox methodCB;
    @FXML
    private CheckBox phoneChargeCB;
    @FXML
    private CheckBox prepaymentCB;
    @FXML
    private ChoiceBox statusCB;
    @FXML
    private DatePicker checkOutDP;
    @FXML
    private TextField guestIdTF;
    @FXML
    private TextField roomIdTF;
    @FXML
    private DatePicker checkInDP;
    @FXML
    private Label totalPriceF;

    public void onSubmitClicked(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int roomId = Integer.parseInt(roomIdTF.getText());
        int guestId = Integer.parseInt(guestIdTF.getText());
        LocalDate checkIn = checkInDP.getValue();
        LocalDate checkOut = checkOutDP.getValue();
        String status = (String) statusCB.getValue();
        Boolean prepayment = prepaymentCB.isSelected();
        Boolean phoneCharge = phoneChargeCB.isSelected();
        String method = (String) statusCB.getValue();
        String cardNumber = null;
        if (!cardTF.getText().isBlank()) {
            cardNumber = cardTF.getText();
        }
        String cardVcc = null;
        if (!vccTF.getText().isBlank()) {
            cardVcc = vccTF.getText();
        }


        Boolean isSummerCharged = summerCB.isSelected();
        Boolean isChristmasCharged = christmasCB.isSelected();
        Boolean isEasterCharged = easterCB.isSelected();
        Boolean isEventCharged = eventCB.isSelected();
        Boolean isUsingLowOccupancyOffer = percentageCB.isSelected();


        totalPrice = Integer.parseInt(priceTF.getText());


        BookDAO.addBook(roomId, guestId, checkIn, checkOut, status, LocalDate.now(), prepayment, phoneCharge, method,
                isSummerCharged, isChristmasCharged, isEasterCharged, isEventCharged, isUsingLowOccupancyOffer,
                Integer.parseInt(priceTF.getText())
                , totalPrice, cardNumber, cardVcc);


    }

    public void checkInUpdated() throws SQLException, ClassNotFoundException {
        int perc = BookDAO.getOccupancyPercentage(checkInDP.getValue());
        percentage.setText(String.valueOf(perc));
        calcFinalPrice();
    }

    public void calcFinalPrice(){
        if (checkInDP != null && checkOutDP != null && !priceTF.getText().isBlank()) {
            long daysBetween = checkInDP.getValue().until(checkOutDP.getValue(), ChronoUnit.DAYS);
            totalPrice = (int) (daysBetween * Long.parseLong(priceTF.getText()));


            if (christmasCB.isSelected() || eventCB.isSelected() || easterCB.isSelected() || summerCB.isSelected()) {
                totalPrice += 15;
            }


            if (percentageCB.isSelected()) {
                totalPrice /= 2;

            }

            totalPriceF.setText(String.valueOf(totalPrice));


        }
    }
}
