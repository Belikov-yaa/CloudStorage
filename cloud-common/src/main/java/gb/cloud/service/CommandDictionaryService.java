package gb.cloud.service;


import gb.cloud.domain.Command;
import gb.cloud.domain.CommandCode;
import io.netty.channel.ChannelHandlerContext;

public interface CommandDictionaryService {

    void processCommand(Command command, ChannelHandlerContext ctx);
    CommandService getCommandService(CommandCode code);
}
