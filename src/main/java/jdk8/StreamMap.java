package jdk8;

import jdk8.pojo.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-02 23:56
 */
public class StreamMap {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 6, 7, 7, 1);

        List<Integer> result = list.stream().map(i -> i * 2).collect(toList());

        System.out.println(result);

        List<String> collect = getDish().stream().map(dish -> dish.getName()).collect(toList());

        System.out.println(collect);

        String[] words = {"hello", "world"};
        Stream<String[]> stream = Arrays.stream(words).map(w -> w.split(""));

        Stream<String> stringStream = stream.flatMap(Arrays::stream);
        stringStream.distinct().forEach(System.out::println);
    }

    private static List<Dish> getDish() {
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
        return menu;
    }
}
