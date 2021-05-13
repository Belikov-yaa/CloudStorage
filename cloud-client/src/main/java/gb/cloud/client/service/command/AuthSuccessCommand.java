package gb.cloud.client.service.command;

import gb.cloud.domain.Command;
import gb.cloud.domain.CommandCode;
import gb.cloud.service.CommandService;
import io.netty.channel.ChannelHandlerContext;

import java.util.function.Consumer;

public class AuthSuccessCommand implements CommandService {
    private Consumer<String[]> consumer;

    @Override
    public void processCommand(Command command, ChannelHandlerContext ctx) {
        if (consumer != null)
            consumer.accept(command.getArgs());
    }

    @Override
    public CommandCode getCommandCode() {
        return CommandCode.CMD_SUCCESS;
    }

    @Override
    public void setListener(Consumer<String[]> consumer) {
        this.consumer = consumer;
    }
}
