package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * 创建缓存区
 * 直接缓存区更快，因为省去了堆内拷贝到堆外的步骤，适合网络发送和接受，堆外分配比堆内慢，netty通过内存池解决这个问题
 * 堆内缓冲区，快速创建快速销毁，且能够获取内部字节数据
 *
 * IO线程使用堆外，业务线程使用堆内
 * @auther wei.wang09
 * @date 2019/9/29
 */
public class ByteBufTest0 {

    public static void main(String[] args) {

        //createDefault();
        //
        //createCopy();

        createCompositeByteBuf();
    }

    private static void createCompositeByteBuf() {
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();

        ByteBuf buffer = Unpooled.buffer(10);
        ByteBuf directBuffer = Unpooled.directBuffer(8);

        compositeByteBuf.addComponents(buffer, directBuffer);

        compositeByteBuf.forEach(System.out::println);
    }

    private static void createCopy() {
        ByteBuf byteBuf = Unpooled.copiedBuffer("豆hello world", Charset.forName("UTF-8"));

        if (byteBuf.hasArray()) {
            byte[] array = byteBuf.array();
            System.out.println(new String(array,Charset.forName("UTF-8")));
            System.out.println(byteBuf);
            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());

            System.out.println(byteBuf.readableBytes());
            System.out.println(byteBuf.writableBytes());

            for (int i = 0; i < byteBuf.readableBytes(); i++) {
                System.out.println((char)byteBuf.getByte(i));
            }

            CharSequence charSequence = byteBuf.getCharSequence(0, 4, Charset.forName("UTF-8"));
            System.out.println(charSequence);
        }
    }

    private static void createDefault() {
        ByteBuf buffer = Unpooled.buffer();//默认长度 256
        //ByteBuf buffer = Unpooled.buffer(10);
        //
        //ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer(10);
        //
        //ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(10);

        //for (int i = 0; i < 10; i++) {
        //    buffer.writeByte(i);
        //}
        //
        //for (int j = 0; j < buffer.capacity(); j++) {
        //    System.out.println(buffer.getByte(j));
        //}
    }
}
