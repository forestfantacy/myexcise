package basic.classinit;

/**
 * @auther wei.wang09
 * @date 2019/10/9
 */
public class TestClassInit0503 {

    public static void main(String[] args) {
        System.out.println(Child0503.tt);//初始化接口，不会初始化父接口
    }
}

interface Parent0503{
    public static Thread t = new Thread(){
        {
            System.out.println("Parent0503 init");
        }
    };
}

interface Child0503 extends Parent0503{
    public static Thread tt = new Thread(){
        {
            System.out.println("Child0503 init");
        }
    };
}