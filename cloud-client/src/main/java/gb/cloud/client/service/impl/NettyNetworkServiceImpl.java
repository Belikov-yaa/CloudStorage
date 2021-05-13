package gb.cloud.client.service.impl;

import gb.cloud.client.service.NetworkService;
import gb.cloud.domain.Command;
import gb.cloud.domain.CommandCode;
import gb.cloud.utils.PropertiesLoader;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NettyNetworkServiceImpl implements NetworkService {

    private static NettyNetworkServiceImpl instance;

    private String serverHost;
    private int serverPort;
    private ExecutorService executorService;
    private NettyConnection nettyConnection;

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
        executorService = Executors.newFixedThreadPool(5);
    }

    @Override
    public void connect(String login, String passhash) {
        if (isConnected()) {
            throw new RuntimeException("Channel already open");
        }
        nettyConnection = new NettyConnection(new Command(CommandCode.AUTHENTICATE, login, passhash), serverPort, serverHost);
        System.out.println(CommandCode.AUTHENTICATE + " " + login + "" + passhash + " " + serverPort + "" + serverHost);
        if (executorService != null) {
            executorService.shutdownNow();
        }
        executorService = Executors.newFixedThreadPool(2);
        executorService.submit(nettyConnection);
    }

    @Override
    public boolean isConnected() {
        return nettyConnection != null && nettyConnection.isConnected();
    }

    @Override
    public void sendCommand(Command command) {
        if (nettyConnection.isConnected()) nettyConnection.sendCommand(command);

    }

    @Override
    public void uploadFile(File file, Path serverUploadDir) {

    }

    @Override
    public void downloadFile(Path file, Path clientDownloadDir) {

    }

    @Override
    public void submitConnection(Runnable connection) {

    }

    @Override
    public void requestFileList(String path) {

    }

    @Override
    public void requestRegistration(String login, String password) {

    }

    @Override
    public void closeConnection() {
        if (isConnected()) {
            nettyConnection.disconnect();
            executorService.shutdown();
        }
    }
}
