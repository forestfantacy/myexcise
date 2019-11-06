package basic.reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @auther wei.wang09
 * @date 2019/10/6
 */
public class GetSetTest {

    public static Object copy(Object object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> aClass = object.getClass();
        Constructor<?> constructor = aClass.getConstructor(new Class[]{});
        Constructor<?> constructor1 = aClass.getConstructor(new Class[]{String.class});

        Object newInstance = constructor.newInstance(new Object[]{});
        Object newInstance1 = constructor1.newInstance(new Object[]{"wangwei"});

        System.out.println(newInstance);
        System.out.println(newInstance1);

        Field[] declaredFields = aClass.getDeclaredFields();

        for (int i = 0; i < declaredFields.length; i++) {
            String fieldName = declaredFields[i].getName();

            String lowName = fieldName.substring(0, 1).toUpperCase();

            String getMethod = "get" + lowName + fieldName.substring(1);
            String setMethod = "set" + lowName + fieldName.substring(1);

            Method method0 = aClass.getMethod(getMethod, new Class[]{});
            Method method1 = aClass.getMethod(setMethod, new Class[]{String.class});

            Object invoke0 = method0.invoke(newInstance, new Object[]{});
            System.out.println(invoke0);

            Object invoke = method0.invoke(newInstance1, new Object[]{});
            System.out.println(invoke);


        }

        return null;
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        copy(new Foo());
    }

    static class Foo{
        private String name;

        public Foo() {
            this.name = name;
        }

        public Foo(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
