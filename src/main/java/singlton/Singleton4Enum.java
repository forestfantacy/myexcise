package singlton;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-02-05 16:47
 */
public class Singleton4Enum {

    private Singleton4Enum(){}

    private enum Singleton{

        INSTANCE;

        private final Singleton4Enum instance;

        //利用了枚举构造函数是线程安全的，且只会被初始化一次的特性
        Singleton(){
            instance = new Singleton4Enum();
        }

        public Singleton4Enum getInstance() {
            return instance;
        }
    }

    public static Singleton4Enum getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
}
