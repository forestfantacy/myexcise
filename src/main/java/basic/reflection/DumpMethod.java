package basic.reflex;

import java.lang.reflect.Method;

/**
 * @auther wei.wang09
 * @date 2019/10/6
 */
public class DumpMethod {

    public static void main(String[] args) throws Exception {

        Class<?> aClass = Class.forName("java.lang.String");

        for (Method method : aClass.getDeclaredMethods()) {
            System.out.println(method);
        }
    }
}
