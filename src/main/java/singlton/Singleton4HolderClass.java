package singlton;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-02-05 16:40
 */
public class Singleton4HolderClass {

    private Singleton4HolderClass(){}

    public Singleton4HolderClass getInstance() {
        //主动加载内部类，此时才会初始化instance，实现了懒加载
        //初始化静态变量是线程安全的，且只会初始化一次
        return InstanceHolder.instance ;
    }

    private static class InstanceHolder{
        private static Singleton4HolderClass instance = new Singleton4HolderClass();
    }
}
