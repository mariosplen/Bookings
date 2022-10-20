package bookings.controllers;

import bookings.models.User;
import bookings.models.UserDAO;
import bookings.util.Views;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    public Label msg;

    public Hyperlink registerHL;
    public RadioButton isAdminRB;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;


    @FXML
    public void onLoginBtnClicked() throws IOException, SQLException, ClassNotFoundException {

        User user = UserDAO.loginUser(usernameTF.getText(), passwordTF.getText());

        if (user == null) {
            msg.setText("Wrong Password Or Username");
            return;
        }
        if (user.isIsAdmin() != isAdminRB.isSelected()) {
            msg.setText("Wrong Password Or Username");
            return;
        }

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root;

        // TODO: FIX DUPLICATE CODE
        if (isAdminRB.isSelected() && user.isIsAdmin()) {
            loader.setLocation(getClass().getResource(Views.ADMIN));
            stage.setTitle("Admin Page");
            loader.load();
            root = loader.getRoot();
            AdminPageController apc = loader.getController();
            apc.GetUserID(usernameTF.getText());
            apc.secondInitialize();

        } else {
            loader.setLocation(getClass().getResource(Views.USER));
            stage.setTitle("User Page");
            loader.load();
            root = loader.getRoot();
            UserPageController upc = loader.getController();
            upc.GetUserID(usernameTF.getText());
            upc.secondInitialize();
        }


        Scene scene = new Scene(root);

        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);

        stage.setScene(scene);
        stage.show();

        ((Stage) usernameTF.getScene().getWindow()).close();
    }


    public void radioButtonClicked() {
        registerHL.setVisible(!isAdminRB.isSelected());
    }
}