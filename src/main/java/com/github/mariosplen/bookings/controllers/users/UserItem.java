package com.github.mariosplen.bookings.controllers.users;

import com.github.mariosplen.bookings.models.UserDAO;
import com.github.mariosplen.bookings.util.Nav;
import com.github.mariosplen.bookings.util.Views;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;

public class UserItem implements Initializable {

    public Rectangle basicPerm;
    public Rectangle manageGuests;
    public Rectangle manageUsers;
    public Text nameTxt;

    public void onChangePassBtnClicked(ActionEvent actionEvent) {

        // Finds current username by going backwards to the node tree
        String username = (((Text) ((Button) actionEvent.getSource())
                .getParent().getChildrenUnmodifiable().get(3))
                .getText());

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Change Password of User: " + username);
        Label enterPass = new Label("Enter new Password");
        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-background-color: #C6B3EF;\n"
                + "    -fx-background-radius: 3;");
        Button save = new Button("Save");
        save.setStyle(" -fx-background-color: #5100FC; -fx-text-fill: #FFFFFF; -fx-background-radius: 6; -fx-font-size: 20; -fx-font-weight: bold; -fx-border-radius: 6; -fx-border-color: #000000; -fx-border-width: 1; -fx-padding: 14;");
        enterPass.setStyle("-fx-font-size: 32; -fx-font-weight: bold;");
        VBox vBox = new VBox(enterPass, passwordField, save);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(40));
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);
        stage.show();
        save.setOnAction(actionEvent1 -> {
            try {
                UserDAO.changePass(username, passwordField.getText());
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            stage.close();
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tooltip basic = new Tooltip("Basic Functionality");

        Tooltip.install(basicPerm, basic);
        Tooltip guestsManage = new Tooltip("Edit Guests");

        Tooltip.install(manageGuests, guestsManage);
        Tooltip usersManage = new Tooltip("Edit Users");

        Tooltip.install(manageUsers, usersManage);

    }

    public void onDeleteBtnClicked(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String username = (((Text) ((Button) actionEvent.getSource())
                .getParent().getChildrenUnmodifiable().get(3))
                .getText());
        UserDAO.deleteUser(username);
        refreshView();

    }

    private void refreshView() throws SQLException, ClassNotFoundException {
        // Refresh mainPane by re-creating loader

        Iterator<Window> windows = Stage.impl_getWindows();
        while (windows.hasNext()) {
            Window window = windows.next();
            Parent p = window.getScene().getRoot();
            if (p.lookup("#contentPane") != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.USERS));
                try {
                    ((BorderPane) (p.lookup("#contentPane"))).setCenter(loader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        Nav.user = UserDAO.login(Nav.user.username(), Nav.user.password());
    }

    public void onPermManageUsersClicked(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {

        String username = handleRectangleClick(mouseEvent);
        UserDAO.switchPerm(username, "manage_users");
        refreshView();
    }

    public void onPermManageGuestsClicked(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        String username = handleRectangleClick(mouseEvent);
        UserDAO.switchPerm(username, "manage_guests");
        refreshView();
    }

    public void onPermBasicClicked(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        String username = handleRectangleClick(mouseEvent);
        UserDAO.switchPerm(username, "basic_functions");
        refreshView();
    }

    private String handleRectangleClick(MouseEvent event) {
        // Get the rectangle that was clicked
        Rectangle rectangle = (Rectangle) event.getSource();

        // Get the HBox containing the rectangle
        HBox hBox = (HBox) rectangle.getParent();

        // Get the HBox containing the HBoxes
        HBox allHBoxes = (HBox) hBox.getParent();

        return ((Text) allHBoxes.getChildren().get(3)).getText();
    }
}
