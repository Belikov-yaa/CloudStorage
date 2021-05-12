package gb.cloud.client.controller;

import gb.cloud.client.classes.MainAppContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    public void btnAuthAction(ActionEvent actionEvent) {
    }

    public void btnRegistrationAction(ActionEvent actionEvent) {
        passwordChekField.setManaged(true);
        passwordChekField.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainAppContext.getInstance().setAuthFormController(this);
    }

}
