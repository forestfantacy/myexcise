package classloader.pojo;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-01-25 09:37
 */
public class SingleTon {

    private static int x = 0;

    private static int y;

    private static SingleTon instance = new SingleTon();

    private SingleTon(){
        x++;
        y++;
    }

    public static SingleTon getSingleTon() {
        return instance;
    }

    public static void main(String[] args) {
        getSingleTon();
        //主动引用
        /**
         *
         *  private static int x = 0;
         *  private static int y;
         *  private static SingleTon instance = new SingleTon();
         * 链接：
         * 1.为静态变量分配内存 x = 0,y = 0,instance = null
         * 2.初始化变量 x = 0,y = 0,instance = new SingleTon()，此时构造被调用 x = 1,y = 1
         */

        /**
         *
         *  private static SingleTon instance = new SingleTon();
         *  private static int x = 0;
         *  private static int y;
         * 链接：
         * 1.为静态变量分配内存 instance = null,x = 0,y = 0,
         * 2.初始化变量 instance = new SingleTon()，此时构造被调用 x = 1,y = 1,然后初始化 x = 0,y 没有处理
         */
    }
}
