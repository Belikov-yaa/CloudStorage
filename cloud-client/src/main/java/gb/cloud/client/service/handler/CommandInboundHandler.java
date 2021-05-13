package gb.cloud.client.service.handler;

import gb.cloud.client.factory.Factory;
import gb.cloud.domain.Command;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CommandInboundHandler extends SimpleChannelInboundHandler<Command> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Command command) throws Exception {
        Factory.getCommandDictionaryService().processCommand(command, ctx);
    }
}
