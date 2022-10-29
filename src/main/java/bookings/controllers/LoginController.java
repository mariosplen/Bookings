package bookings.controllers;

import bookings.models.UserDAO;
import bookings.util.Views;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

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

        Boolean login = UserDAO.login(usernameTF.getText(), passwordTF.getText());

        if (!login) {
            msg.setText("Wrong Password Or Username");
            return;
        }


        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();

        loader.setLocation(getClass().getResource(Views.MAIN));

        loader.load();
        MainController mainController = loader.getController();
        mainController.setUsername(usernameTF.getText());
        mainController.lateInitialize();

        Scene scene = new Scene(loader.getRoot());

        //        JMetro jMetro = new JMetro(Style.LIGHT);
        //        jMetro.setScene(scene);

        stage.setScene(scene);
        stage.setMinHeight(700);
        stage.setMinWidth(1200);
        stage.show();

        ((Stage) usernameTF.getScene().getWindow()).close();
    }


    public void radioButtonClicked() {
        registerHL.setVisible(!isAdminRB.isSelected());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Print usernames and passwords to console
        try {
            UserDAO.getUsers().forEach(user -> System.out.println(user.getUsername() + " " + user.getPassword() + " is " + "perm: " + user.getPerm()));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}