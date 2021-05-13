package gb.cloud.client.controller;

import gb.cloud.client.classes.MainAppContext;
import gb.cloud.client.factory.Factory;
import gb.cloud.domain.Command;
import gb.cloud.domain.CommandCode;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private MainAppContext mainAppContext;

    @FXML
    public HBox authForm;

    @FXML
    VBox leftPanel, rightPanel;



    public void exitApp() {
        mainAppContext.getNetworkService().closeConnection();
        System.out.println("close connection");
        Platform.exit();
    }

    public void copyBtnAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainAppContext = MainAppContext.getInstance();
        mainAppContext.setMainController(this);
        mainAppContext.setNetworkService(Factory.getNetworkService());

        Factory.getCommandDictionaryService().getCommandService(CommandCode.CMD_SUCCESS).setListener(this::loginSuccessAction);
    }

    private void loginSuccessAction(String[] args) {
        authForm.setVisible(false);
        authForm.setManaged(false);
        mainAppContext.getNetworkService().requestFileList("/");
        Factory.getCommandDictionaryService().getCommandService(CommandCode.REQUEST_FILE_LIST).setListener(mainAppContext.getServerPanelController()::updateFileList);
    }

    public void logout(ActionEvent actionEvent) {
    }
}