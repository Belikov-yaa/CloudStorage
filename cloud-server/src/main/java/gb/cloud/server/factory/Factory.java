package gb.cloud.server.factory;

import gb.cloud.server.service.impl.NettyServerService;
import gb.cloud.server.service.ServerService;

public class Factory {
    public static ServerService getServerService() {
        return new NettyServerService();
    }
}
