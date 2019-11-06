package jdk8;

import jdk8.pojo.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-01 16:20
 */
public class LamdaUsage {

    private static List<Apple> filterApples(List<Apple> source, Predicate<Apple> predicate){

        ArrayList<Apple> results = new ArrayList<>();

        for (Apple apple : source) {
            if (predicate.test(apple)) {
                results.add(apple);
            }
        }

        return results;
    }

    private static List<Apple> filterByWeight(List<Apple> apples, IntPredicate predicate) {
        ArrayList<Apple> results = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple.getWeight())) {
                results.add(apple);
            }
        }
        return results;
    }

    private static List<Apple> filterByWeight(List<Apple> apples, BiPredicate<String,Integer> predicate) {
        ArrayList<Apple> results = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple.getColor(),apple.getWeight())) {
                results.add(apple);
            }
        }
        return results;
    }

    private static void simpleTestConsumer(List<Apple> apples, Consumer<Apple> consumer) {
        for (Apple apple : apples) {
            consumer.accept(apple);
        }
    }

    private static void simpleTestBiConsumer(List<Apple> apples, BiConsumer<String,Integer> consumer) {
        for (Apple apple : apples) {
            consumer.accept(apple.getColor(),apple.getWeight());
        }
    }

    private static Integer testFunction(Apple apple, Function<Apple, Integer> function) {
        return function.apply(apple);
    }

    private static Integer testBiFunction(Apple apple, BiFunction<Integer,Integer, Integer> function) {
        return function.apply(apple.getWeight(),apple.getWeight());
    }

    private static Apple createApple(Supplier<Apple> supplier) {
        return supplier.get();
    }
    /**
     *
     * predicate boolean test(T t) 解决条件判断的逻辑抽象
     * Consumer accept（T t） 没有实际的额外逻辑，解决回调逻辑抽取问题
     * Function<T,R> R apple(T) 输入T，返回R，能抽象最复杂的逻辑，可指定了入参和返回值
     * Supplieer<T> T get() ?
     *
     * @param args
     */
    public static void main(String[] args) {


        Runnable r1 = () -> System.out.println("");

        List<Apple> apples = Arrays.asList(new Apple("green", 150), new Apple("red", 100));

        List<Apple> green = filterApples(apples, (apple -> apple.getColor().equals("green")));

        List<Apple> above100 = filterByWeight(apples, weight -> weight > 100);

        List<Apple> bi = filterByWeight(apples, (c, w) -> c.equals("green") && w > 100);

        //System.out.println(green);
        //System.out.println(above100);
        //System.out.println(bi);
        //
        //System.out.println("==============================================");
        //simpleTestConsumer(apples, apple -> System.out.println(apple));
        //simpleTestBiConsumer(apples, (c, w) -> System.out.println(c + "-" + w));

        Integer redAppleWeight = testFunction(new Apple("red", 100), apple -> apple.getWeight() * 3);
        System.out.println(redAppleWeight);

        //IntFunction与Function的区别是，参数类型不用再声明了
        IntFunction<Double> f = i -> i * 100d;
        Double apply = f.apply(10);
        System.out.println(apply);

        System.out.println(testBiFunction(new Apple("red", 100), (w, s) -> w + s));

        Apple red = createApple(() -> new Apple("red", 100));
        System.out.println(red);
    }

}
