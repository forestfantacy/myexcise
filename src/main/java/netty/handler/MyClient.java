package netty.handler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.time.LocalDateTime;

/**
 * @auther wei.wang09
 * @date 2019/9/30
 */
public class MyClient {


    public static void main(String[] args) throws Exception {

        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors).channel(NioSocketChannel.class).
                    handler(new MyClientInitalizer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }

    private static class MyClientInitalizer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast(new MyServer.MyByteToLongDecoder());
            pipeline.addLast(new MyServer.MyLongToByteEncoder());

            pipeline.addLast(new MyClientHandler());
        }
    }

    private static class MyClientHandler extends SimpleChannelInboundHandler<Long> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
            System.out.println(ctx.channel().remoteAddress());
            System.out.println("client output:" + msg);
            //这句不会报错，会被服务端丢弃
            ctx.channel().writeAndFlush("from client:" + LocalDateTime.now());
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(123456L);
        }
    }
}
