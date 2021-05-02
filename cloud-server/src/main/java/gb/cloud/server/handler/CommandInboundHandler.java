package gb.cloud.server.handler;

import gb.cloud.domain.Command;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

public class CommandInboundHandler implements ChannelHandler {
//    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Command command) {
//        CommandDictionaryService dictionaryService = Factory.getCommandDirectoryService();
//        System.out.println(command);
//        String commandResult = dictionaryService.processCommand(command);

//        ctx.writeAndFlush(commandResult);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {

    }
}
