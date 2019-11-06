package jdk8;

import jdk8.pojo.Apple;
import jdk8.pojo.ComplexApple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-01 17:43
 */
public class MethodReference {


    private static <T> void useConsumer(Consumer<T> consumer, T t) {
        consumer.accept(t);
        consumer.accept(t);
    }

    public static void main(String[] args) {

        useConsumer(s -> System.out.println(s), "hello world");

        List<Apple> apples = Arrays.asList(new Apple("red", 150), new Apple("green", 100));

        apples.sort(Comparator.comparing(Apple::getColor));

        System.out.println(apples);

        apples.stream().forEach(apple -> System.out.println(apple));

        System.out.println("=========================================");

        //这里System.out::println 就是方法推导的用法
        apples.stream().forEach(System.out::println);

        //静态方法推导 Integer::parseInt
        int value = Integer.parseInt("123");
        Function<String,Integer> f = Integer::parseInt;
        Integer result = f.apply("123");
        System.out.println(result);

        String ss = new String("hello");
        //实例方法的推导
        Function<Integer,Character> f2 = ss::charAt;
        Character character = f2.apply(2);
        System.out.println(character);

        //构造函数推导
        BiFunction<String, Integer, Apple> appleFunction = Apple::new;
        //apply时喂2个参数
        Apple red = appleFunction.apply("red", 100);

        ThreeFunction<String, String, Integer, ComplexApple> threeFunction = ComplexApple::new;
        ComplexApple apply = threeFunction.apply("fushi", "red", 100);

    }
}
