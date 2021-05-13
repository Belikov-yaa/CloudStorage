package gb.cloud.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerPanelController implements Initializable {
    public TableView filesTable;
    public TextField pathField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnPathUpAction(ActionEvent actionEvent) {
    }

    public void updateFileList(String[] strings) {
    }
}
