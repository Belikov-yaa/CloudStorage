package gb.cloud.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthFormController {
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
}
