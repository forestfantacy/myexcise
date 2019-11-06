package jdk8;

import jdk8.pojo.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-02 13:56
 */
public class SimpleStream {

    public static void main(String[] args) {

        List<Dish> menu = Arrays.asList(
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

        //List<String> dishnameByCollections = getDishnameByCollections(menu);
        //System.out.println(dishnameByCollections);
        //
        //List<String> dishnameByStream = getDishnameByStream(menu);
        //System.out.println(dishnameByStream);

        //testShunxu(menu);

        Stream<Dish> dishStream = Stream.of(new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        dishStream.forEach(System.out::println);

    }

    /**
     * 过滤
     * 排序
     * 获取集合中对象名字集合
     */
    private static List<String> getDishnameByCollections(List<Dish> menu) {
        ArrayList<Dish> results = new ArrayList<>();

        //filter
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                results.add(dish);
            }
        }

        //sort
        results.sort(Comparator.comparingInt(Dish::getCalories));

        ArrayList<String> results2 = new ArrayList<>();
        //抽取
        for (Dish dish : results) {
            results2.add(dish.getName());
        }
        return results2;
    }

    private static List<String> getDishnameByStream(List<Dish> menu) {
        return menu.stream().filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName).collect(toList());
    }

    private static List<String> getDishnameByParallelStream(List<Dish> menu) {
        return menu.parallelStream()
                .filter(
                    dish -> {
                        try {
                            Thread.currentThread().sleep(100000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return dish.getCalories() < 400;

                    }
                 )
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName).collect(toList());
    }

    private static List<String> testShunxu(List<Dish> menu) {
        List<String> collect = menu.stream().filter(dish -> {
            System.out.println("filter==>" + dish.getName());
            return dish.getCalories() < 400;
        }).map(dish -> {
            System.out.println("map==>" + dish.getName());
            return dish.getName();
        }).collect(toList());
        System.out.println("==================================");
        System.out.println(collect);
        return collect;
    }
}
