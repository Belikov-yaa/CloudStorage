package gb.cloud.client.service.impl;

import gb.cloud.client.service.NetworkService;

public class IONetworkService implements NetworkService {

    /* Вынести настройки в файл properties */
    @Override
    public void sendCommand(String command) {

    }

    @Override
    public String readCommandResult() {
        return null;
    }

    @Override
    public void closeConnection() {

    }
}
