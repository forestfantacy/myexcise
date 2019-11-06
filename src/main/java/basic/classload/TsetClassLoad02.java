package basic.classload;

/**
 * @auther wei.wang09
 * @date 2019/10/9
 */
public class TsetClassLoad02 {

    public static void main(String[] args) throws ClassNotFoundException {
        //系统类加载器就是应用类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Class<?> aClass = systemClassLoader.loadClass("basic.classload.CL02");
        System.out.println(aClass); //类加载并不会导致类初始化，不算是主动使用

        System.out.println("------");

        aClass = Class.forName("basic.classload.CL02"); //反射会导致初始化
        System.out.println(aClass);
    }
}

class CL02{
    static {
        System.out.println("Class CL");
    }
}
