package gb.cloud.client.service;

public interface NetworkService {

    void connect(String login, String passhash);

    boolean isConnected();

    void sendCommand(String command) ;

    String readCommandResult();

    void closeConnection();
}
