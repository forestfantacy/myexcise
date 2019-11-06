package utinities.datastruct.map;

import org.junit.Test;

import java.util.concurrent.ConcurrentSkipListMap;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-03-29 08:47
 *
 * ConcurrentSkipMapTest 是对treemap的高并发实现，
 * 换句话说，它同时解决了两个问题：key的排序和高并发
 *
 * treemap 的key能不能是对象？可以，且通常要实现comparable接口
 */
public class ConcurrentSkipListMapTest {

    static ConcurrentSkipListMap<Integer, String> skipListMap = create();

    static {

        skipListMap.put(1, "Scala");
        skipListMap.put(5, "Java");
        skipListMap.put(10, "Clojure");
    }

    @Test
    public void testCeiling(){

        assertThat(skipListMap.size(), equalTo(3));
        assertThat(skipListMap.ceilingKey(3), equalTo(5));
        assertThat(skipListMap.ceilingEntry(3).getValue(),equalTo("Java"));
    }

    @Test
    public void testFloor(){
        assertThat(skipListMap.floorKey(2), equalTo(1));
        assertThat(skipListMap.floorEntry(2).getValue(),equalTo("Scala"));
    }

    @Test
    public void testFirst(){
        assertThat(skipListMap.firstKey(), equalTo(1));
        assertThat(skipListMap.firstEntry().getValue(),equalTo("Scala"));
    }

    @Test
    public void testLast(){
        assertThat(skipListMap.lastKey(), equalTo(10));
        assertThat(skipListMap.lastEntry().getValue(),equalTo("Clojure"));
    }

    @Test
    public void testMerge(){

        skipListMap.merge(1, "C++", (ov, v) -> {
            assertThat(ov, equalTo("Scala"));
            assertThat(v, equalTo("C++"));
            return ov + v;
        });

        assertThat(skipListMap.get(1), equalTo("ScalaC++"));

        skipListMap.merge(3, "python", (ov, v) -> {
            //只有在ov不为null时，这个回调函数才会执行
            return ov + v;
        });

        System.out.println(skipListMap);
    }

    @Test
    public void testCompute(){
        skipListMap.compute(1, (k, v) -> {
            assertThat(k, equalTo(1));
            assertThat(v, equalTo("Scala"));
            return "hello";
        });

        assertThat(skipListMap.get(1),equalTo("hello"));

        skipListMap.compute(3, (k, v) -> {
            assertThat(k, equalTo(3));
            assertThat(v, equalTo(null));
            return "newHello";
        });

        assertThat(skipListMap.get(3),equalTo("newHello"));
    }

    public static <K,V>ConcurrentSkipListMap<K,V> create(){
        return new ConcurrentSkipListMap<>();
    }

}
