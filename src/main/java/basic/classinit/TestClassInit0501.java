package basic.classinit;

import java.util.Random;

/**
 * @auther wei.wang09
 * @date 2019/10/9
 */
public class TestClassInit0501 {

    public static void main(String[] args) {
        //System.out.println(Child05.b);//引用类非final，一定会加载
        System.out.println(Child05.c);//引用类final，编译期常量池处理
        //System.out.println(Child5.b);//引用接口的编译期常量，接口不会被加载，因为Test5在编译期已经确定b，并放到自己的常量池中
        //System.out.println(Child5.bb);//运行期常量，接口会被加载，缺class文件会报错
    }
}

interface Parent5{
    public static int a = 6; //接口的常量默认就是final的，索引引用接口常量不会引发加载接口的class文件
}

interface Child5 extends Parent5{
    public static int b = 5;
    public static int bb = new Random().nextInt(3);

}

class Child05 implements Parent5{
    public static int b = 5;
    public final static int c = 5;
}