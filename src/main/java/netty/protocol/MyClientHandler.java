package netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @auther wei.wang09
 * @date 2019/10/1
 */
public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();
        System.out.println("客户端收到 No."+ ++count + " 条消息：");
        System.out.println("长度："+length);
        System.out.println("内容：" + new String(content, Charset.forName("utf-8")));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            String msgSent = "send from client";
            byte[] content = msgSent.getBytes(Charset.forName("utf-8"));
            int length = msgSent.length();
            PersonProtocol personProtocol = new PersonProtocol();
            personProtocol.setContent(content);
            personProtocol.setLength(length);
            ctx.writeAndFlush(personProtocol);
        }
    }
}
