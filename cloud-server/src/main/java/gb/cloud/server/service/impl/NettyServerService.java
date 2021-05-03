package gb.cloud.server.service.impl;

import gb.cloud.server.handler.CommandInboundHandler;
import gb.cloud.server.service.ServerService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyServerService implements ServerService {

    @Override
    public void startServer(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

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
            System.out.println("Server stopped");
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
