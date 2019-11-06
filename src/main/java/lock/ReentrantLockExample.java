package lock;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther wei.wang09
 * @date 2019/7/11
 */
public class ReentrantLockExample {

    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        //IntStream.range(0,2).forEach(i -> new Thread(){
        //    @Override
        //    public void run() {
        //        needLock();
        //    }
        //}.start());

        // --------------------------

        //Thread thread1 = new Thread(() -> testInterruptibly());
        //thread1.start();
        //TimeUnit.SECONDS.sleep(1);
        //
        //Thread thread2 = new Thread(() -> testInterruptibly());//第2个线程被扔到lock主，因为第1个线程持有锁
        //thread2.start();
        //TimeUnit.SECONDS.sleep(1);
        //
        //thread2.interrupt();  //打断 thread1
        //System.out.println("==================");

        // --------------------------

        Thread thread3 = new Thread(() -> testTryLock());
        thread3.start();
        TimeUnit.SECONDS.sleep(1);

        Thread thread4 = new Thread(() -> testTryLock());
        thread4.start();
        TimeUnit.SECONDS.sleep(18);
    }

    public static void needLock() {
        try {
            lock.lock();
            Optional.of("the thread " + Thread.currentThread().getName() + " get lock and will do work").ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void needLockBySync() {

        synchronized (ReentrantLockExample.class) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // --------------------------


    public static void testInterruptibly() {
        try {
            lock.lockInterruptibly(); //thread2.interrupt() 可以打断 thread1
            Optional.of("the thread " + Thread.currentThread().getName() + " get lock and will do work").ifPresent(System.out::println);
            while (true) {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void testTryLock() {

        try {
            if (lock.tryLock(8,TimeUnit.SECONDS)) {
                try {
                    Optional.of("the thread " + Thread.currentThread().getName() + " get lock and will do work").ifPresent(System.out::println);
                    while (true) {

                    }
                } finally {
                    lock.unlock();
                }
            } else {
                Optional.of("the thread " + Thread.currentThread().getName() + " NOT get lock and will do work").ifPresent(System.out::println);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Optional.of("the thread " + Thread.currentThread().getName() + " interrupted from waiting lock").ifPresent(System.out::println);

        }

    }
}
