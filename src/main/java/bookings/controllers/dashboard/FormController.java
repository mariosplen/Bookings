package bookings.controllers.dashboard;

import bookings.models.BookDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;

public class FormController {


    @FXML
    private Label percentage;
    @FXML
    private Label totalPrice;
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
        if(!cardTF.getText().isBlank()){
            cardNumber = cardTF.getText();
        }
        String cardVcc = null;
        if(!vccTF.getText().isBlank()){
            cardVcc = vccTF.getText();
        }




        int totalPrice = Integer.parseInt(priceTF.getText());


        BookDAO.addBook(roomId,guestId,checkIn,checkOut,status,LocalDate.now(),prepayment,phoneCharge,method,
                        totalPrice, cardNumber,cardVcc);


    }

    public void checkInUpdated() throws SQLException, ClassNotFoundException {
        int perc = BookDAO.getOccupancyPercentage(checkInDP.getValue());
        percentage.setText(String.valueOf(perc));
        calcFinalPrice();
    }

    public void calcFinalPrice(){
        if(checkInDP!=null && checkOutDP!=null && !priceTF.getText().isBlank()){
            long daysBetween = checkInDP.getValue().until(checkOutDP.getValue(), ChronoUnit.DAYS);
            priceTF.setText(String.valueOf(daysBetween*Long.parseLong(priceTF.getText())));
        }
    }
}
