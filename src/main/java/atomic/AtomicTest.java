package atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-02-12 03:40
 */
public class AtomicTest {

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger i = new AtomicInteger(10);

        boolean flag = i.compareAndSet(12, 20);

        System.out.println(i);
        System.out.println(flag);

        TimeUnit.SECONDS.sleep(2);
    }
}
