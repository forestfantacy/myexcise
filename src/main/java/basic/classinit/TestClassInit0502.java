package basic.classinit;

/**
 * @auther wei.wang09
 * @date 2019/10/9
 */
public class TestClassInit0502 {

    public static void main(String[] args) {
        System.out.println(Child0502.b);//主动使用导致Child0502被初始化，实现接口不会被初始化
        //如果改成Child0502 继承 父类，那么父类会被初始化
    }
}

interface Parent0502{
    public static Thread tt = new Thread(){
        {
            System.out.println("Parent0502 init");
        }
    };
}

class Child0502 implements Parent0502{
    public static int b = 5;
}