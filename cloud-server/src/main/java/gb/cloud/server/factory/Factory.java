package gb.cloud.server.factory;

import gb.cloud.server.service.impl.NettyServerService;
import gb.cloud.server.service.ServerService;
import gb.cloud.server.service.DB;
import gb.cloud.server.service.impl.PostgreDBImpl;

public class Factory {
    public static ServerService getServerService() {
        return NettyServerService.getInstance();
    }

    public static DB getDB() {
        return PostgreDBImpl.getInstance();
    }
}
