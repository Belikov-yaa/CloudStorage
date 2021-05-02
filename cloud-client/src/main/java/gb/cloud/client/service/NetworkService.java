package gb.cloud.client.service;

public interface NetworkService {

    void connect();

    boolean isConnected();

    void sendCommand(String command) ;

    String readCommandResult();

    void closeConnection();
}
