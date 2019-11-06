package jdk8;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-08 17:08
 */
public class FutureInAction2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService.submit(() -> {
            try {
                Thread.sleep(10000);
                return "finished";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "error";
            }
        });

        String s = submit.get();
        System.out.println(s);
        executorService.shutdown();
    }
}
