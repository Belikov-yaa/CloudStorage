package gb.cloud.service;

import gb.cloud.domain.Command;
import gb.cloud.domain.CommandCode;
import io.netty.channel.ChannelHandlerContext;

import java.util.function.Consumer;

public interface CommandService {
    void processCommand(Command command, ChannelHandlerContext ctx);
    CommandCode getCommandCode();
    default void setListener(Consumer<String[]> consumer){};
}
