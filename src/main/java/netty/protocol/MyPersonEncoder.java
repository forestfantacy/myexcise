package netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @auther wei.wang09
 * @date 2019/10/1
 */
public class MyPersonEncoder extends MessageToByteEncoder<PersonProtocol>{
    @Override
    protected void encode(ChannelHandlerContext ctx, PersonProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyPersonEncoder invoked");
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
