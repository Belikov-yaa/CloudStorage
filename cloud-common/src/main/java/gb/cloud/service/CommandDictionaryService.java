package gb.cloud.service;


import gb.cloud.domain.Command;
import gb.cloud.domain.CommandName;
import io.netty.channel.ChannelHandlerContext;

/**
 * Connects CommandCode and Class in commands package
 */
public interface CommandDictionaryService {

    void processCommand(Command command, ChannelHandlerContext ctx);
    CommandService getCommandService(CommandName code);
}
