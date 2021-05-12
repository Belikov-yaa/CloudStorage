package gb.cloud.client.classes;

import gb.cloud.client.controller.AuthFormController;
import gb.cloud.client.controller.FilePanelController;
import gb.cloud.client.controller.MainController;
import gb.cloud.client.controller.ServerPanelController;

public class MainAppContext {
    private static MainAppContext instance;

    private AuthFormController authFormController;
    private MainController mainController;
    private FilePanelController filePanelController;
    private ServerPanelController serverPanelController;

    private MainAppContext() {
    }

    public static MainAppContext getInstance() {
        if (instance == null) instance = new MainAppContext();
        return instance;
    }

    public AuthFormController getAuthFormController() {
        return authFormController;
    }

    public void setAuthFormController(AuthFormController authFormController) {
        this.authFormController = authFormController;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public FilePanelController getFilePanelController() {
        return filePanelController;
    }

    public void setFilePanelController(FilePanelController filePanelController) {
        this.filePanelController = filePanelController;
    }

    public ServerPanelController getServerPanelController() {
        return serverPanelController;
    }

    public void setServerPanelController(ServerPanelController serverPanelController) {
        this.serverPanelController = serverPanelController;
    }

}
