package utinities.datastruct.queue;

import org.junit.Test;

import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-03-30 07:39
 */
public class PriorityBlockingQueueTest {

    PriorityBlockingQueue queue;

    @Test
    public void testAddNewElement(){
        queue = create(5);
        assertThat(queue.add("hello1"),equalTo(true));
        assertThat(queue.add("hello2"),equalTo(true));
        assertThat(queue.add("hello3"),equalTo(true));
        assertThat(queue.add("hello4"),equalTo(true));
        assertThat(queue.add("hello5"),equalTo(true));
        assertThat(queue.add("hello6"),equalTo(true));
        assertThat(queue.size(),equalTo(6));
    }

    @Test
    public void testGetElement() throws InterruptedException {
        queue = create(3);
        assertThat(queue.add("hello4"),equalTo(true));
        assertThat(queue.add("hello2"),equalTo(true));
        assertThat(queue.add("hello3"),equalTo(true));

        assertThat(queue.element(),equalTo("hello2"));
        assertThat(queue.size(),equalTo(3));
        assertThat(queue.element(),equalTo("hello2"));
        assertThat(queue.size(),equalTo(3));
        ////////////////////////////////////
        assertThat(queue.peek(),equalTo("hello2"));
        assertThat(queue.size(),equalTo(3));
        assertThat(queue.peek(),equalTo("hello2"));
        assertThat(queue.size(),equalTo(3));
        ////////////////////////////////////
        assertThat(queue.poll(),equalTo("hello2"));
        assertThat(queue.size(),equalTo(2));
        assertThat(queue.poll(),equalTo("hello3"));
        assertThat(queue.size(),equalTo(1));
        ////////////////////////////////////
        assertThat(queue.take(),equalTo("hello4"));
        assertThat(queue.size(), equalTo(0));

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(() -> queue.add("newdata"), 3, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();

        assertThat(queue.take(),equalTo("newdata"));
        assertThat(queue.size(),equalTo(0));


    }

    @Test(expected = ClassCastException.class)
    public void testAddObject_with_nocomparable(){
        PriorityBlockingQueue<Object> queue = create(3);
        queue.add(new UserWithNoComparable());
        fail("should not here");
    }

    @Test
    public void testAddObject_with_comparable(){
        PriorityBlockingQueue<UserWithNoComparable> queue = create(3, Comparator.comparingInt(Object::hashCode));
        queue.add(new UserWithNoComparable());
        assertThat(queue.size(),equalTo(1));
    }

    private <T> PriorityBlockingQueue<T> create(int size, java.util.Comparator comparator) {
        return new PriorityBlockingQueue<T>(size, comparator);

    }

    public <T> PriorityBlockingQueue<T> create(int size){
        return new PriorityBlockingQueue<T>(size);
    }

    static class UserWithNoComparable{

    }
}
