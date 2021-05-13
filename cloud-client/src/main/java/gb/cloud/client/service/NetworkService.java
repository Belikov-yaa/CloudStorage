package gb.cloud.client.service;

import gb.cloud.domain.Command;

import java.io.File;
import java.nio.file.Path;
import java.util.function.Consumer;

public interface NetworkService {

    void connect(String login, String passhash);
    void closeConnection();
    boolean isConnected();

    void sendCommand(Command command) ;

    void uploadFile(File file, Path serverUploadDir);
    void downloadFile(Path file, Path clientDownloadDir);

    void submitConnection(Runnable connection);
    void requestFileList(String path);
    void requestRegistration(String login, String password);
}
