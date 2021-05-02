package gb.cloud.client.factory;

import gb.cloud.client.service.NetworkService;
import gb.cloud.client.service.impl.NettyNetworkServiceImpl;

public class Factory {

    public static NetworkService getNetworkService() {
        return NettyNetworkServiceImpl.getInstance();
    }
}
