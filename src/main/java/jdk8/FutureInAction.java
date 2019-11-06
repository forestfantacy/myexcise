package jdk8;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Thread.*;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-08 16:43
 */
public class FutureInAction {

    public static void main(String[] args) throws InterruptedException {

        Future<String> future = invoke(() -> {
            try {
                sleep(10000);
                return "finished";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "error";
            }
        });
        System.out.println(future.get());
        while (!future.isDone()) {
            sleep(10);
        }
        System.out.println(future.get());

    }

    private static <T> Future<T> invoke(Callable<T> callable) {

        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean isDone = new AtomicBoolean(false);

        Thread t = new Thread(()->{

            T action = callable.action();
            result.set(action);
            isDone.set(true);

        });
        t.start();

        Future<T> future = new Future<T>(){

            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return isDone.get();
            }
        };

        return future;
    }

    private interface Future<T>{

        T get();

        boolean isDone();
    }

    private interface Callable<T> {

        T action();
    }

}
