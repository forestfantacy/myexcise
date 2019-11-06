package utinities.datastruct.queue;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-03-30 10:39
 *
 * 解决消费者限时消费的问题
 */
public class SyncronousQueueTest {

    @Test(expected = IllegalStateException.class)
    public void testAdd_no_reader(){
        SynchronousQueue<String> queue = create();
        queue.add("hello");
        fail("no here");
    }

    @Test
    public void testAdd_has_reader() throws InterruptedException {
        SynchronousQueue<String> queue = create();
        Executors.newSingleThreadExecutor().submit(()->{
            try {
                assertThat(queue.take(),equalTo("syncronous"));//阻塞1s
                assertThat(queue.take(),equalTo("syncronous"));//阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        TimeUnit.SECONDS.sleep(1);
        assertThat(queue.add("syncronous"), equalTo(true));
    }

    @Test
    public void testOffer_fail() throws InterruptedException {
        SynchronousQueue<String> queue = create();
        assertThat(queue.offer("syncronous"), equalTo(false));
    }

    @Test
    public void testOffer_succ() throws InterruptedException {
        SynchronousQueue<String> queue = create();
        Executors.newSingleThreadExecutor().submit(()->{
            try {
                assertThat(queue.take(),equalTo("syncronous"));//阻塞1s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        TimeUnit.SECONDS.sleep(1);
        assertThat(queue.offer("syncronous"), equalTo(true));
    }

    @Test
    public void testPut() throws InterruptedException {
        SynchronousQueue<String> queue = create();
        queue.put("hello");
        fail("no here");
    }

    public <T> SynchronousQueue<T> create(){
        return new SynchronousQueue<>();
    }
}
