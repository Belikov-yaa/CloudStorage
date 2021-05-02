package gb.cloud.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/views/mainApp.fxml"));
        primaryStage.setTitle("Java CloudStorage Manager [GeekBrains]");
        primaryStage.setScene(new Scene(root, 1280, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
