package gb.cloud.server.service;

public interface DB {

    void connect();

    boolean getUser(String login, String password);

    boolean addUser(String login, String password);

    void closeConnection();

}
