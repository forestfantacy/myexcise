package codec;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 * @auther wei.wang09
 * @date 2019/8/30
 */
public class Test {

    static Kryo kryo;

    static {
        kryo = new Kryo();
        kryo.setRegistrationRequired(false);
        kryo.setMaxDepth(20);
        kryo.register(TransactionReportPointFlink.class);

    }

    public static void main(String[] args) throws Exception {
        kryoSerieal();
    }

    static public void kryoSerieal () throws Exception {

        long timestamp0 = new Date().getTime();

        String type = "URLTest4";
        String name = "URLTest4";
        String region = "LogicA";
        String group = "default_gouop";
        String domain = "luckycapi";
        String ip1 = "1.1.1.1";


        TransactionReportPointFlink reportPointCountFlink0 = new TransactionReportPointFlink(
                "maxTime.luckycapi.URLTest4.60000", Arrays.asList("maxTime","maxTime","maxTime","maxTime","maxTime","maxTime","maxTime"), 60000, timestamp0,Arrays.asList(1d,1d,1d,1d,1d,1d,1d),type,name,region,group,domain,ip1);
        //序列化1条，10000次
        int leng = 0;
        long start0 = System.currentTimeMillis();
        for (int i = 0; i < 80; i++) {
            byte[] bytesFromData = serialize(reportPointCountFlink0);
            leng += bytesFromData.length;
        }
        long start1 = System.currentTimeMillis();
        System.out.println("序列化1条，10000次 = " + (start1 - start0) );
        System.out.println("序列化1条，10000次 = " + leng);

    }

    public static byte[] serialize(Object obj) {
        ByteArrayOutputStream out = null;
        Output output = null;
        try {
            out = new ByteArrayOutputStream();
            output = new Output(out, 1024);
            kryo.writeClassAndObject(output, obj);
            return output.toBytes();
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != out) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                }
            }
            if (null != output) {
                output.close();
                output = null;
            }
        }
    }
}
