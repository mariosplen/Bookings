package bookings.controllers;

import bookings.application.DbConnection;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public void onLoginBtnClicked() throws IOException, SQLException {
        Connection con = DbConnection.Connection();
        if (con == null) throw new SQLException("con is null");

        PreparedStatement ps;
        ResultSet rs;

        ps = con.prepareStatement("SELECT * FROM users WHERE username = ? and password = ?");
        ps.setString(1, usernameTF.getText());
        ps.setString(2, passwordTF.getText());

        rs = ps.executeQuery();
        if (rs.next()) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();


            if (isAdminRB.isSelected() && rs.getBoolean("is_admin")) {
                loader.setLocation(getClass().getResource(Views.ADMIN));
                stage.setTitle("Admin Page");
            } else if (!isAdminRB.isSelected() && !rs.getBoolean("is_admin")) {
                loader.setLocation(getClass().getResource(Views.USER));
                stage.setTitle("User Page");
            } else {
                msg.setText("Wrong Password Or UserID");
                ps.close();
                rs.close();
                con.close();
                return;
            }

            loader.load();

            Parent root = loader.getRoot();
            UserPageController upc = loader.getController();
            upc.GetUserID(usernameTF.getText());
            upc.secondInitialize();

            Scene scene = new Scene(root);

            JMetro jMetro = new JMetro(Style.LIGHT);
            jMetro.setScene(scene);

            stage.setScene(scene);
            stage.show();

            ((Stage) usernameTF.getScene().getWindow()).close();
        } else {
            msg.setText("Wrong Password Or UserID");
        }
        ps.close();
        rs.close();
        con.close();
    }


    public void radioButtonClicked() {
        registerHL.setVisible(!isAdminRB.isSelected());
    }
}