package jdk8;

import jdk8.pojo.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-01 10:47
 */
public class FilterApple {

    public static List<Apple> findApples(List<Apple> apples) {

        ArrayList<Apple> filtedApps = new ArrayList<>();

        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                filtedApps.add(apple);
            }
        }
        return filtedApps;
    }

    public static List<Apple> findApples(List<Apple> apples,String color) {

        ArrayList<Apple> filtedApps = new ArrayList<>();

        for (Apple apple : apples) {
            if (color.equals(apple.getColor())) {
                filtedApps.add(apple);
            }
        }
        return filtedApps;
    }

    public static List<Apple> findApples(List<Apple> apples,AppleFilter filter) {

        ArrayList<Apple> filtedApps = new ArrayList<>();

        for (Apple apple : apples) {
            if (filter.filter(apple)) {
                filtedApps.add(apple);
            }
        }
        return filtedApps;
    }

    @FunctionalInterface
    public interface AppleFilter {
        boolean filter(Apple apple);
    }


    public static void main(String[] args) throws InterruptedException {

        List<Apple> apples = Arrays.asList(new Apple("green", 150), new Apple("red", 30));

        //根据绿色过滤
        List<Apple> filtedApps = findApples(apples);

        //根据颜色过滤
        List<Apple> filtedApps2 = findApples(apples, "green");

        //根据颜色和重量过滤，把过滤规则抽象成策略模式
        List<Apple> filtedApps3 = findApples(apples, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                return apple.getColor().equals("green") && apple.getWeight() > 100;
            }
        });

        //更简洁的lamda
        List<Apple> filtedApps4 = findApples(apples,
                apple -> apple.getColor().equals("green") && apple.getWeight() > 100);

        assert filtedApps.size() == 2 : "filter app error";

        System.out.println(filtedApps);

        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        //run方法作为函数方法，
        //@FunctionalInterface
        //public interface Runnable {}
        // 且无参数，可用()表示
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        Thread.currentThread().join();

        Supplier<Apple> s = Apple::new;
        Apple apple = s.get();

    }
}
