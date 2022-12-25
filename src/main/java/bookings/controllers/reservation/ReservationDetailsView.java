package bookings.controllers.reservation;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReservationDetailsView implements Initializable {
    public Text roomIDText;
    public Text categoryText;
    public Text numberOfNightsText;
    public Text finalPriceText;
    public TextField doorPrice;
    public CheckBox christmasCB;
    public TextField christmassPriceTF;
    public CheckBox easterCB;
    public TextField easterTF;
    public CheckBox summerCB;
    public TextField summerTF;
    public CheckBox eventCB;
    public TextField eventTF;
    public Text errorMsg;
    public Button continueBtn;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String roomId;
    private String category;
    private int numberOfNights;
    private BorderPane mainPane;


    @FXML
    private void refresh() {
        // Calculate the base price based on the number of nights and door price
        int basePrice = Integer.parseInt(numberOfNightsText.getText()) * Integer.parseInt(doorPrice.getText());
        finalPriceText.setText(String.valueOf(basePrice));

        // If the Christmas checkbox is selected, add the Christmas price to the base price
        if (christmasCB.isSelected()) {
            int christmasPrice = Integer.parseInt(numberOfNightsText.getText()) * Integer.parseInt(christmassPriceTF.getText());
            finalPriceText.setText(String.valueOf(basePrice + christmasPrice));
        }
        if (easterCB.isSelected()) {
            int christmasPrice = Integer.parseInt(numberOfNightsText.getText()) * Integer.parseInt(easterTF.getText());
            finalPriceText.setText(String.valueOf(basePrice + christmasPrice));
        }
        if (eventCB.isSelected()) {
            int christmasPrice = Integer.parseInt(numberOfNightsText.getText()) * Integer.parseInt(eventTF.getText());
            finalPriceText.setText(String.valueOf(basePrice + christmasPrice));
        }
        if (summerCB.isSelected()) {
            int christmasPrice = Integer.parseInt(numberOfNightsText.getText()) * Integer.parseInt(summerTF.getText());
            finalPriceText.setText(String.valueOf(basePrice + christmasPrice));
        }
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMainPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            // Set the category text
            categoryText.setText(category);

            // Calculate the number of nights between the check in and check out dates
            int numberOfNights = checkInDate.until(checkOutDate).getDays();
            numberOfNightsText.setText("Number of nights: " + numberOfNights);
            roomIDText.setText("room ID: " + roomId);
        });

        // Calculate the final price based on the number of nights and door price
        int finalPrice = numberOfNights * Integer.parseInt(doorPrice.getText());
        finalPriceText.setText(String.valueOf(finalPrice));

        // If the Christmas checkbox is selected, add the Christmas price to the final price
        if (christmasCB.isSelected()) {
            finalPrice += numberOfNights * Integer.parseInt(christmassPriceTF.getText());
            finalPriceText.setText(String.valueOf(finalPrice));
        }
    }
}




