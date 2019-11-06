package basic.annotation.test1;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @auther wei.wang09
 * @date 2019/10/6
 */
public class MyReflectionMain {


    /**
     * 通过反射获取到 Class Method，因为他们都实现了annotationElement接口
     * 从而可以获取注解上所有的信息，在编译期可以根据这些信息实现逻辑，比如 。。。
     *
     *
     *
     * @param args
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class<?> aMyServiceClass = Class.forName("basic.annotation.test1.MyService");
        Object aMyService = aMyServiceClass.newInstance();

        Method doSthMethod = aMyServiceClass.getMethod("doo", new Class[]{});

        if (doSthMethod.isAnnotationPresent(MyAnnotation1.class)) {
            doSthMethod.invoke(aMyService, new Object[] {});

            MyAnnotation1 myAnnotation1 = doSthMethod.getAnnotation(MyAnnotation1.class);
            String hello = myAnnotation1.hello();
            String world = myAnnotation1.world();
            System.out.println(hello);
            System.out.println(world);
        }

        for (Annotation annotation : doSthMethod.getAnnotations()) {
            System.out.println(annotation.annotationType().getName());
        }

    }
}
