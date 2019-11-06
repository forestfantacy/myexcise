package utinities.datastruct.queue;

import org.junit.Test;

import java.util.Iterator;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-03-30 11:18
 */
public class DelayQueueTest {

    @Test
    public void testAdd() throws InterruptedException {
        DelayQueue<DelayElement<String>> delayQueue = create();
        DelayElement<String> delayed1 = DelayElement.of("delayed1", 1500);
        delayQueue.add(delayed1);
        long start = System.currentTimeMillis();

        assertThat(delayQueue.size(), equalTo(1));
        //assertThat(delayQueue.peek(), is(delayed1)); //没有延时
        assertThat(delayQueue.take(), is(delayed1)); //有延时指定时间
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void testAdd2() throws InterruptedException {
        DelayQueue<DelayElement<String>> delayQueue = create();
        delayQueue.add(DelayElement.of("delayed1", 1500));
        delayQueue.add(DelayElement.of("delayed2", 500));
        delayQueue.add(DelayElement.of("delayed3", 900));
        delayQueue.add(DelayElement.of("delayed4", 1200));
        delayQueue.add(DelayElement.of("delayed5", 800));

        assertThat(delayQueue.size(), equalTo(5));
        long start = System.currentTimeMillis();

        Iterator<DelayElement<String>> iterator = delayQueue.iterator();
        while (iterator.hasNext()) {
            assertThat(iterator.next(),notNullValue());
        }
        System.out.println(System.currentTimeMillis() - start);
        assertThat(System.currentTimeMillis() - start < 5, equalTo(true)); //没有延时
    }

    @Test
    public void testAdd3() throws InterruptedException {
        DelayQueue<DelayElement<String>> delayQueue = create();
        delayQueue.add(DelayElement.of("delayed1", 1500));
        delayQueue.add(DelayElement.of("delayed2", 500));
        delayQueue.add(DelayElement.of("delayed3", 900));
        delayQueue.add(DelayElement.of("delayed5", 800));

        //按照超时时间排序
        assertThat(delayQueue.take().getData(), equalTo("delayed2"));
        assertThat(delayQueue.take().getData(), equalTo("delayed5"));
        assertThat(delayQueue.take().getData(), equalTo("delayed3"));
        assertThat(delayQueue.take().getData(), equalTo("delayed1"));
    }


    @Test
    public void testAdd5() throws InterruptedException {
        DelayQueue<DelayElement<String>> delayQueue = create();
        delayQueue.add(DelayElement.of("delayed1", 1500));
        delayQueue.add(DelayElement.of("delayed2", 50));
        delayQueue.add(DelayElement.of("delayed3", 900));
        delayQueue.add(DelayElement.of("delayed5", 800));

        TimeUnit.MILLISECONDS.sleep(70);
        //按照超时时间排序
        assertThat(delayQueue.poll().getData(), equalTo("delayed2"));
    }

    @Test
    public void testAdd6() {
        DelayQueue<DelayElement<String>> delayQueue = create();
        try {
            delayQueue.add(null);
        } catch (Exception e) {
            assertThat(e, is(NullPointerException.class));

        }
    }

    @Test
    public void testpoll() {
        DelayQueue<DelayElement<String>> delayQueue = create();
        assertThat(delayQueue.poll(), nullValue());

    }

    static class DelayElement<E> implements Delayed{

        private final E e;

        private final long expireTime;

        public DelayElement(E e, long expireTime) {
            this.e = e;
            this.expireTime = System.currentTimeMillis() + expireTime;
        }

        static <T> DelayElement<T> of(T t, long delay) {
            return new DelayElement<>(t, delay);
        }
        @Override
        public long getDelay(TimeUnit unit) {
            long diff = expireTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed delayed) {
            DelayElement that = (DelayElement) delayed;
            if (this.expireTime > that.expireTime) {
                return 1;
            } else if (this.expireTime < that.expireTime) {
                return -1;
            }else{
                return 0;
            }
        }

        public E getData(){
            return e;
        }
    }

    public <T extends Delayed> DelayQueue<T> create(){
        return new DelayQueue<>();
    }
}
