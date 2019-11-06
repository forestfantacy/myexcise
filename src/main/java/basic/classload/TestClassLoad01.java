package basic.classload;

/**
 * @auther wei.wang09
 * @date 2019/10/9
 */
public class TestClassLoad01 {

    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> aClass0 = Class.forName("java.lang.String");
        System.out.println(aClass0.getClassLoader());

        Class<?> aClass1 = Class.forName("basic.classload.A");
        System.out.println(aClass1.getClassLoader());//AppClassLoader A类在classpath

    }
}

class A{

}
