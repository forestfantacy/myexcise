package jdk8;

import jdk8.pojo.Dish;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-02-21 08:51
 */
public class CollectorsAction {

    static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEET),
            new Dish("beaf", false, 700, Dish.Type.MEET),
            new Dish("chicke", false, 400, Dish.Type.MEET),
            new Dish("french fries", false, 530, Dish.Type.OTHER),
            new Dish("rice", false, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    public static void main(String[] args) {
        //testAveragingDouble();
        //testAveragingInt();
        //testCollectingAndThen1();
        //testCollectingAndThen2();
        //testCounting();
        //groupingByFunction();
        //groupingByFunctionAndCollector1();
        //groupingByFunctionAndCollector2();
        //groupingByFunctionAndSupplierAndCollector();
        groupingByFunctionAndSupplierAndCollector2();
        //groupingBySummarizingInt();
    }

    private static void testAveragingInt(){
        System.out.println("===========================================");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testAveragingDouble(){
        System.out.println("===========================================");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))//小类型转换到大类型，隐式转换
                .ifPresent(System.out::println);
    }

    private static void testCollectingAndThen1(){
        System.out.println("===========================================");
        Optional.ofNullable(menu.stream().collect
                //先算平均值，collect到集合，then给集合元素做二次处理
                (Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories), a -> "average is =>" + a)))
                .ifPresent(System.out::println);
    }

    private static void testCollectingAndThen2(){
        System.out.println("===========================================");
        Optional.ofNullable(menu.stream()
                //先过滤，collect到集合，然后用collectingAndThen添加二次逻辑，把元素集合到新的不变集合
                .filter(dish -> dish.getType().equals(Dish.Type.MEET)).collect(
                (Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList))))
                .ifPresent(System.out::println);
        //menu.add(aNewDish);//报错
    }

    private static void testCounting(){
        System.out.println("===========================================");
        Optional.ofNullable(menu.stream().collect(Collectors.counting()))
                .ifPresent(System.out::println);
    }

    private static void groupingByFunction(){
        System.out.println("===========================================");
        Collector<Dish, ?, Map<Dish.Type, List<Dish>>> dishMapCollector = Collectors.groupingBy(Dish::getType);
        Optional.ofNullable(menu.stream().collect(dishMapCollector))
                .ifPresent(System.out::println);
        System.out.println(menu);
    }

    private static void groupingByFunctionAndCollector1(){
        System.out.println("===========================================");
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.counting())))
                .ifPresent(System.out::println);
    }

    private static void groupingByFunctionAndCollector2(){
        System.out.println("===========================================");
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.averagingInt(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    private static void groupingByFunctionAndSupplierAndCollector(){
        System.out.println("===========================================");
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType, TreeMap::new,Collectors.averagingInt(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    private static void groupingByFunctionAndSupplierAndCollector2(){
        System.out.println("===========================================");
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType, TreeMap::new,
                Collectors.groupingBy(Dish::getCalories,TreeMap::new,Collectors.summarizingInt(Dish::getCalories)
                ))))
                .ifPresent(System.out::println);
    }

    private static void groupingBySummarizingInt(){
        System.out.println("===========================================");
        Optional.ofNullable(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void groupingBySummarizingInt2(){
        System.out.println("===========================================");
        List<Long> longs = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        //LongSummaryStatistics collect = longs.stream().collect(Collectors.summarizingLong());
        //collect.getMax();

    }
}
