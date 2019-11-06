package classloader;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-01-25 14:18
 */
public class BootClassLoader {


    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));

        Class<?> aClass = Class.forName("classloader.pojo.SingleTon");
        System.out.println(aClass.getClassLoader());
        System.out.println(aClass.getClassLoader().getParent());
    }
}
