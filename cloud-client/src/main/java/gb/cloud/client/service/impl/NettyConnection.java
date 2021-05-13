package gb.cloud.client.service.impl;

import gb.cloud.client.service.handler.CommandInboundHandler;
import gb.cloud.domain.Command;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyConnection implements Runnable {

    private final Command firstCommand;
    private SocketChannel socketChannel;
    private final int port;
    private final String server;

    public NettyConnection(Command command, int serverPort, String serverHost) {
        this.firstCommand = command;
        this.server = serverHost;
        this.port = serverPort;
    }

    @Override
    public void run() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) {
                            socketChannel = channel;
                            channel.pipeline().addLast(
                                    new ObjectEncoder(),
                                    new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                                    new CommandInboundHandler()
                            );

                        }
                    });
            ChannelFuture future = b.connect(server, port).sync();
            System.out.println("2");
            if (firstCommand != null) {
                socketChannel.writeAndFlush(firstCommand);
                System.out.println("3");

            }
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

    public void sendCommand(Command command) {
        if (isConnected()) socketChannel.writeAndFlush(command);
        System.out.println(command.toString());
    }

    public void disconnect() {
        if (isConnected()) socketChannel.close();
    }


    public boolean isConnected() {
        return socketChannel != null && socketChannel.isActive();
    }

}
