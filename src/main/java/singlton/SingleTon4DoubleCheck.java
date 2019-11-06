package singlton;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-02-05 16:32
 *
 * 双检查锁，优化了性能，
 */
public class SingleTon4DoubleCheck {

    //但是如果要对象构造过程很复杂，那么就存在还没有构造好就被使用的情况，然而加入volatile不够高效，可以有不加的实现方案
    private static volatile SingleTon4DoubleCheck instance;

    private SingleTon4DoubleCheck(){}

    public static SingleTon4DoubleCheck getInstance() {

        if (null == instance) {
            synchronized (SingleTon4DoubleCheck.class) {
                if (null == instance) {
                    instance = new SingleTon4DoubleCheck();//volatile 保护
                }
            }
        }
        return SingleTon4DoubleCheck.instance;
    }

}
