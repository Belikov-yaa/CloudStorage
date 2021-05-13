package gb.cloud.server.service;

import io.netty.channel.Channel;

public interface ServerService {
    void startServer(int port);
    void subscribeUser(String login, Channel channel);
    void unsubscribeUser(Channel channel);

    boolean isUserAuthenticated(Channel channel);
}
