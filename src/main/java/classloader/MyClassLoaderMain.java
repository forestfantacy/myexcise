package classloader;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-01-28 21:04
 */
public class MyClassLoaderMain {


    public static void main(String[] args) throws ClassNotFoundException {
        test2();
    }
    /**
     * 加载的任务优先交给父加载器
     *
     * 父子加载器是包装关系，不是继承关系
     *
     * @throws ClassNotFoundException
     */
    public static void test1() throws ClassNotFoundException {

        MyClassLoader myFirstClassLoader1 = new MyClassLoader("My first ClassLoader");
        MyClassLoader myFirstClassLoader2 = new MyClassLoader("My second ClassLoader");
        //MyClassLoader myFirstClassLoader2 = new MyClassLoader("My second ClassLoader",myFirstClassLoader1);
        myFirstClassLoader2.setDir("/Users/forest/logs/0129/");
        Class<?> aClass = myFirstClassLoader2.loadClass("classloader.pojo.MyTestObj");
        MyClassLoader whoClassLoader = (MyClassLoader)aClass.getClassLoader();
        System.out.println(whoClassLoader.getClassloadName());
    }

    public static void test2() throws ClassNotFoundException {

        MyClassLoader myFirstClassLoader1 = new MyClassLoader("My first ClassLoader");
        MyClassLoader myFirstClassLoader2 = new MyClassLoader("My second ClassLoader");
        myFirstClassLoader2.setDir("/Users/forest/logs/0129/");
        Class<?> aClass0 = myFirstClassLoader1.loadClass("classloader.pojo.MyTestObj");
        Class<?> aClass1 = myFirstClassLoader1.loadClass("classloader.pojo.MyTestObj");
        Class<?> aClass2 = myFirstClassLoader2.loadClass("classloader.pojo.MyTestObj");
        System.out.println(aClass0.hashCode());
        System.out.println(aClass1.hashCode());
        System.out.println(aClass2.hashCode());
    }
}
