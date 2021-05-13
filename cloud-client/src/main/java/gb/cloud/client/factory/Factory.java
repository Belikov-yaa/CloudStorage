package gb.cloud.client.factory;

import gb.cloud.client.service.NetworkService;
import gb.cloud.client.service.impl.NettyNetworkServiceImpl;
import gb.cloud.service.CommandDictionaryService;
import gb.cloud.service.CommandService;
import gb.cloud.service.impl.CommandDictionaryServiceImpl;
import gb.cloud.utils.ClassInstanceSetBuilder;

public class Factory {

    private static CommandDictionaryService commandDictionaryService;

    public static NetworkService getNetworkService() {
        return NettyNetworkServiceImpl.getInstance();
    }

    public static CommandDictionaryService getCommandDictionaryService() {
        if (commandDictionaryService == null) {
            commandDictionaryService = new CommandDictionaryServiceImpl(ClassInstanceSetBuilder.build(
                    "gb.cloud.client.service.command", CommandService.class));
        }
        return commandDictionaryService;
    }
}
