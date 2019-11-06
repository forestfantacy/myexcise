package basic.reflex;

import java.lang.reflect.Field;

/**
 * @auther wei.wang09
 * @date 2019/10/6
 */
public class FieldTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {

        Private aPrivate = new Private();
        Class<? extends Private> aClass = aPrivate.getClass();

        Private aPrivateObj = aClass.newInstance();
        Field field = aClass.getDeclaredField("name");

        field.setAccessible(true);
        field.set(aPrivateObj, "ww");
        System.out.println(field.get(aPrivateObj));
    }

    static class Private{
        private String name;

    }
}
