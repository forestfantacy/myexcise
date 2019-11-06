package netty.handler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @auther wei.wang09
 * @date 2019/9/30
 */
public class MyServer {

    public static void main(String[] args) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup worderGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, worderGroup).channel(NioServerSocketChannel.class).
                    childHandler(new MyServerInitalizer());

            ChannelFuture sync = serverBootstrap.bind(8899).sync();
            sync.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            worderGroup.shutdownGracefully();
        }
    }

    private static class MyServerInitalizer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {

            System.out.println(this);

            ChannelPipeline pipeline = ch.pipeline();
            //入站
            pipeline.addLast(new MyByteToLongDecoder());
            pipeline.addLast(new MyLong2StringDecoder());
            //出栈
            pipeline.addLast(new MyLongToByteEncoder());
            //业务
            pipeline.addLast(new MyServerHandler());
        }
    }

    private static class MyServerHandler extends SimpleChannelInboundHandler<String> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            System.out.println(ctx.channel().remoteAddress() + "," + msg);
            ctx.channel().writeAndFlush(654321L);
        }
    }

    public static class MyLongToByteEncoder extends MessageToByteEncoder<Long> {

        @Override
        protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
            System.out.println("encode invoked");
            System.out.println(msg);
            out.writeLong(msg);
        }
    }

    public static class MyByteToLongDecoder extends ByteToMessageDecoder {
        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
            System.out.println("decode invoked");
            System.out.println(in.readableBytes());

            if (in.readableBytes()>=8) {
                out.add(in.readLong());
            }else{
                //多余的字节忽略，留给调用者继续积累
            }

        }
    }

    public static class MyLong2StringDecoder extends MessageToMessageDecoder<Long>{

        @Override
        protected void decode(ChannelHandlerContext ctx, Long msg, List<Object> out) throws Exception {
            System.out.println("MyLong2StringDecoder invoded");
            out.add(String.valueOf(msg));
        }
    }
}
