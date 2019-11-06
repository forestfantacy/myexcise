package concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-03-28 08:26
 */
public class ScheduledExecutorServiceExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);

        //test1(scheduledThreadPoolExecutor);
        //
        //test2(scheduledThreadPoolExecutor);
        //
        //test3(scheduledThreadPoolExecutor);

        //test4(scheduledThreadPoolExecutor);
    }

    private static void test4(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread().getName() + " i am running   " + System.currentTimeMillis());
        }, 1, 2, TimeUnit.SECONDS);
    }

    private static void test3(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        ScheduledFuture<?> fixFuture = scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName() + " i am running   " + System.currentTimeMillis());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 2, TimeUnit.SECONDS);
    }

    private static void test2(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) throws InterruptedException, ExecutionException {
        ScheduledFuture<Integer> scheduleHasReturn = scheduledThreadPoolExecutor.schedule(() -> 2, 2, TimeUnit.SECONDS);

        System.out.println(scheduleHasReturn.get());
    }

    private static void test1(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        ScheduledFuture<?> scheduledFuture = scheduledThreadPoolExecutor.schedule(() -> System.out.println("i will be invoked"), 2, TimeUnit.SECONDS);

        scheduledFuture.cancel(true);
    }
}
