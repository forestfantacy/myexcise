package jdk8;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-08 17:22
 */
public class FutureInAction3 {

    public static void main(String[] args) {

        Future<String> future = invoke(() -> {

            try {
                Thread.sleep(10000);
                return "Ok";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "error";
            }
        });

        future.setCompletable(new Completable<String>() {
            @Override
            public void complete(String action) {
                System.out.println("callback " + action);
            }

            @Override
            public void exception(Throwable throwable) {
                System.out.println("callback " + throwable.getMessage());
            }
        });
    }

    private static <T> Future<T> invoke(Callable<T> callable) {

        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean isDone = new AtomicBoolean(false);

        Future<T> future = new Future<T>(){

            Completable<T> Completable;

            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return isDone.get();
            }

            @Override
            public void setCompletable(Completable<T> completable) {
                this.Completable = completable;
            }

            @Override
            public Completable<T> getCompletable() {
                return this.Completable;
            }
        };

        Thread t = new Thread(()->{

            try {
                T action = callable.action();
                result.set(action);
                isDone.set(true);

                if (future.getCompletable() != null) {
                    future.getCompletable().complete(action);
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (future.getCompletable() != null) {
                    future.getCompletable().exception(e);
                }
            }

        });
        t.start();



        return future;
    }

    private interface Future<T>{

        T get();

        boolean isDone();

        void setCompletable(Completable<T> completable);

        Completable<T> getCompletable();
    }

    private interface Callable<T> {

        T action();
    }

    private interface Completable<T>{
        void complete(T action);

        void exception(Throwable throwable);
    }
}
