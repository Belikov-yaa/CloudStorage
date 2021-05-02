package gb.cloud.client.service.impl;

import gb.cloud.client.service.NetworkService;
import gb.cloud.utils.PropertiesLoader;

public class NettyNetworkServiceImpl implements NetworkService {

    private static NettyNetworkServiceImpl instance;

    private String serverHost;
    private int serverPort;

    private boolean connected;

    public static NettyNetworkServiceImpl getInstance() {
        if (instance == null) {
            instance = new NettyNetworkServiceImpl();
            initProperties();
        }
        return instance;
    }

    private static void initProperties() {
        instance.serverHost = PropertiesLoader.getProperty("server.address");
        instance.serverPort = Integer.parseInt(PropertiesLoader.getProperty("server.port"));
    }

    private NettyNetworkServiceImpl() {
    }

    @Override
    public void connect() {
        connected = true;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void sendCommand(String command) {

    }

    @Override
    public String readCommandResult() {
        return null;
    }

    @Override
    public void closeConnection() {
        if (connected) {
            connected = false;
        }
    }
}
