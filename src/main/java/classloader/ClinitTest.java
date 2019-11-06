package classloader;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-01-25 11:42
 */
public class ClinitTest {

    public static void main(String[] args) {

        new Thread(()->new SimpleObject()).start();
        new Thread(()->new SimpleObject()).start();
    }


    static class SimpleObject{

        private static AtomicBoolean init = new AtomicBoolean(true);

        static {
            System.out.println(Thread.currentThread().getName() + " will start");
            while (init.get()) {

            }
            System.out.println(Thread.currentThread().getName() + " will end");
        }
    }
}
