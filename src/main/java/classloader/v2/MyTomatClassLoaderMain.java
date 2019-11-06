package classloader.v2;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-01-28 22:00
 */
public class MyTomatClassLoaderMain {

    public static void main(String[] args) throws ClassNotFoundException {
        MyTomatClassLoader myFirstClassLoader = new MyTomatClassLoader("MyTomatClassLoaderMain");
        Class<?> aClass = myFirstClassLoader.loadClass("classloader.v2.MyTestObjV2");

        System.out.println(aClass.getClassLoader());
    }
}
