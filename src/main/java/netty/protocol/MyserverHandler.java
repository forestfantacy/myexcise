package netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @auther wei.wang09
 * @date 2019/10/1
 */
public class MyserverHandler extends SimpleChannelInboundHandler<PersonProtocol>{

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {

        byte[] content = msg.getContent();
        int length = msg.getLength();

        System.out.println("服务端接收到 No."+ ++count +"的数据：");
        System.out.println("长度，内容：" + length + ":" + new String(content, Charset.forName("utf-8")));

        byte[] uuid = UUID.randomUUID().toString().getBytes(Charset.forName("utf-8"));
        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setLength(uuid.length);
        personProtocol.setContent(uuid);

        ctx.writeAndFlush(personProtocol);
    }
}
