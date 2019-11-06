package netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @auther wei.wang09
 * @date 2019/10/1
 *
 */
public class MyPersonDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        System.out.println("MyPersonDecoder invoked!");
        int length = in.readInt();

        byte[] content = new byte[length];
        in.readBytes(content);

        // 上面的逻辑中，replaying解码器如何解决content数据不完整的问题的？如果不够，抛error，然后回退，所以在网络质量差的情况下，会反复读取、报错、读取，性能很差，官方的方案是用检查点来优化

        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setContent(content);
        personProtocol.setLength(length);

        out.add(personProtocol);
    }
}
