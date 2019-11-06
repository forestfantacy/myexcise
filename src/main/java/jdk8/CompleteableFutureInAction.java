package jdk8;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-08 17:54
 */
public class CompleteableFutureInAction {

    public static void main(String[] args) {

        CompletableFuture<Double> completeableFuture = new CompletableFuture<>();

        new Thread(() -> {
            double value = doBiz();
            completeableFuture.complete(value);
        }).start();

        completeableFuture.whenComplete((v, t) -> {
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(x->x.printStackTrace());
        });
    }

    private static double doBiz() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 0d;
    }

}
