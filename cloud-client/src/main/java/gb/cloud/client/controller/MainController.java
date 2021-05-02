package gb.cloud.client.controller;

import gb.cloud.client.factory.Factory;
import gb.cloud.client.service.NetworkService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    VBox leftPanel, rightPanel;

    private NetworkService networkService;


    public void exitApp(ActionEvent actionEvent) {
        networkService.closeConnection();
        Platform.exit();
    }

    public void copyBtnAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        networkService = Factory.getNetworkService();
    }
}