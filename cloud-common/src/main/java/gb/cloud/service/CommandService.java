package gb.cloud.service;

import gb.cloud.domain.Command;
import gb.cloud.domain.CommandCode;
import io.netty.channel.ChannelHandlerContext;

public interface CommandService {
    void processCommand(Command command, ChannelHandlerContext ctx);
    CommandCode getCommandCode();
}
