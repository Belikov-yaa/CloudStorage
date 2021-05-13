package gb.cloud.client.controller;

import gb.cloud.client.classes.MainAppContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthFormController implements Initializable {
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public PasswordField passwordChekField;
    public Label labelPassChek;

    public void btnAuthAction(ActionEvent actionEvent) {
        if (!MainAppContext.getInstance().getNetworkService().isConnected()) {
            MainAppContext.getInstance().getNetworkService().connect(loginField.getText().trim(), passwordField.getText().trim());
            passwordField.setText("");
        }
    }

    public void btnRegistrationAction(ActionEvent actionEvent) {
        if(!passwordChekField.isManaged()) {
            labelPassChek.setManaged(true);
            passwordChekField.setManaged(true);
            passwordChekField.setVisible(true);
        } else {
            passwordChekField.setManaged(false);
            passwordChekField.setVisible(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainAppContext.getInstance().setAuthFormController(this);
    }

}
