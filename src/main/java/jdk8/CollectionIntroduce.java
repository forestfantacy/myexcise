package jdk8;

import jdk8.pojo.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-06 23:00
 */
public class CollectionIntroduce {

    public static void main(String[] args) {

        List<Apple> apples = Arrays.asList(
                new Apple("green", 150),
                new Apple("green", 120),
                new Apple("green", 90),
                new Apple("red", 100)
        );

        Optional.ofNullable(groupAppleByColor(apples)).ifPresent(System.out::println);
        Optional.ofNullable(groupAppleByFunctional(apples)).ifPresent(System.out::println);
        Optional.ofNullable(groupAppleByCollector(apples)).ifPresent(System.out::println);

        Optional.ofNullable(apples.stream().collect(Collectors.groupingBy(Apple::getColor, Collectors.counting()))).ifPresent(System.out::println);
    }

    private static Map<String, List<Apple>> groupAppleByColor(List<Apple> apples) {

        Map<String, List<Apple>> groupApples = new HashMap<>();

        for (Apple apple : apples) {
            List<Apple> colorApps = groupApples.get(apple.getColor());
            if (colorApps == null) {
                colorApps = new ArrayList<>();
                groupApples.put(apple.getColor(), colorApps);
            }
            colorApps.add(apple);
        }
        return groupApples;
    }

    private static Map<String, List<Apple>> groupAppleByFunctional(List<Apple> apples) {

        Map<String, List<Apple>> groupApples = new HashMap<>();
        apples.stream().forEach(a -> {
            List<Apple> colorApps = groupApples.get(a.getColor());
            if (colorApps == null) {
                colorApps = new ArrayList<>();
                groupApples.put(a.getColor(), colorApps);
            }
            colorApps.add(a);
        });
        return groupApples;
    }

    private static Map<String, List<Apple>> groupAppleByCollector(List<Apple> apples) {
        return apples.stream().collect(Collectors.groupingBy(Apple::getColor));
    }
}