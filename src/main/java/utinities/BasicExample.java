package utinities;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.currentThread;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-03-16 22:12
 */
public class BasicExample {

    public static void main(String[] args) {

        int nextInt = ThreadLocalRandom.current().nextInt();

        Thread thread = currentThread();

    }
}
