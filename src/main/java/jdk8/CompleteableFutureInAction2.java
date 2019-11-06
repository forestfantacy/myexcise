package jdk8;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-10 09:03
 */
public class CompleteableFutureInAction2 {

    public static void main(String[] args) throws InterruptedException {

        AtomicBoolean finished = new AtomicBoolean(false);

        //supply 表示没有入参，但是有返回值的函数表达式
        CompletableFuture<Double> supplyAsync = CompletableFuture.supplyAsync(() -> doBiz());

        supplyAsync.whenComplete((v,t)->{
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(System.out::println);
            finished.set(true);
        });

        System.out.println("===== block ======");

        while (!finished.get()) {
            sleep(10);
        }
    }


    private static double doBiz() {
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 0d;
    }

}
