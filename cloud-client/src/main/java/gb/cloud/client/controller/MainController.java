package gb.cloud.client.controller;

import gb.cloud.client.classes.MainAppContext;
import gb.cloud.client.factory.Factory;
import gb.cloud.client.service.NetworkService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static MainController instance;

    @FXML
    public HBox authForm;

    @FXML
    VBox leftPanel, rightPanel;

    private NetworkService networkService;

    public static MainController getInstance() {
        return instance;
    }


    public void exitApp(ActionEvent actionEvent) {
        networkService.closeConnection();
        Platform.exit();
    }

    public void copyBtnAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainAppContext.getInstance().setMainController(this);
        networkService = Factory.getNetworkService();
    }

    public void logout(ActionEvent actionEvent) {
    }
}