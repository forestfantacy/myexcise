package basic.reflex;

import java.lang.reflect.Array;

/**
 * @auther wei.wang09
 * @date 2019/10/6
 */
public class ArrayTest {


    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> aClass = Class.forName("java.lang.String");

        Object array = Array.newInstance(aClass, 10);

        System.out.println(array);

        Array.set(array, 5, "No5");
        Object o = Array.get(array, 5);
        Object o4 = Array.get(array, 4);
        System.out.println(o);
        System.out.println(o4);


        int[] dims = new int[]{3, 5, 8};
        System.out.println(Integer.TYPE);
        System.out.println(Integer.class);

        Object oo = Array.newInstance(Integer.TYPE, dims);
        System.out.println(Array.get(dims, 2));
        Object oobj = Array.get(oo, 2);
        System.out.println(oobj);
    }
}
