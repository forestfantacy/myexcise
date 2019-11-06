package jdk8.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-02-21 15:44
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    @Override
    public Supplier<List<T>> supplier() {
        System.out.println("supplier");
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        System.out.println("accumulator");
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        System.out.println("combiner");
        return (list1,list2)->{
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        System.out.println("finisher");
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("hello", "world", "bei", "jing", "zhong", "guan", "cun");
        Collector<String, List<String>, List<String>> collector = new ToListCollector<>();

        //List<String> collect = strings.stream().filter(e -> e.length() > 3).collect(collector);
        List<String> collect = strings.parallelStream().filter(e -> e.length() > 3).collect(collector);
        System.out.println(collect);
    }
}
