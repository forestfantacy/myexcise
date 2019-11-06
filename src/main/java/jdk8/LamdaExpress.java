package jdk8;

import com.google.common.base.Predicate;
import jdk8.pojo.Apple;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-01 11:25
 */
public class LamdaExpress {

    public static void main(String[] args) {

        Comparator<Apple> byColor = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };

        List<Apple> apples = Arrays.asList(new Apple("green", 140), new Apple("red", 100));
        Collections.sort(apples, byColor);

        Comparator<Apple> byColor2 = (o1,o2) -> o1.getColor().compareTo(o2.getColor());
        Collections.sort(apples, byColor2);

        Comparator<Apple> byColor3 = Comparator.comparing(Apple::getColor);
        Collections.sort(apples, byColor3);

        System.out.println(apples);

        /**
         *
         * (o1,o2) -> o1.getColor().compareTo(o2.getColor());
         * 左边是  (o1,o2) 参数列表
         * 右边是  lamda body
         * 返回值是 function<入参，返回值><
         *
         */

        Function<String, Integer> ff = (String s) -> s.length();

        Predicate<Apple> green = (Apple apple) -> apple.getColor().equals("green");
    }
}
