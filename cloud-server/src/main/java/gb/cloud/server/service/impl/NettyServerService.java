package gb.cloud.server.service.impl;

import gb.cloud.server.factory.Factory;
import gb.cloud.server.handler.CommandInboundHandler;
import gb.cloud.server.service.ServerService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.ConcurrentHashMap;

public class NettyServerService implements ServerService {
    private static NettyServerService instance;
    private ConcurrentHashMap<Channel, String> activeUserChannel;

    public static ServerService getInstance() {
        if (instance == null) instance = new NettyServerService();
        return instance;
    }

    @Override
    public void startServer(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        activeUserChannel = new ConcurrentHashMap<>();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) {
                            channel.pipeline().addLast(
                                    new ObjectEncoder(),
                                    new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                                    new CommandInboundHandler()
                            );
                        }
                    });

            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println("Server started");
            future.channel().closeFuture().sync(); // block
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Server stopped");
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            Factory.getDB().closeConnection();
        }
    }

    @Override
    public void subscribeUser(String login, Channel channel) {
        activeUserChannel.put(channel, login);
    }

    @Override
    public void unsubscribeUser(Channel channel) {
        activeUserChannel.remove(channel);
    }

    @Override
    public boolean isUserAuthenticated(Channel channel) {
        return activeUserChannel.containsKey(channel);
    }

}
