package classloader;

import java.util.Random;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-01-25 09:09
 */
public class ClassActiveUse {

    public static void main(String[] args) throws ClassNotFoundException {
        //System.out.println(I.i);

        //反射创建类
        //Class<?> aClass = Class.forName("classloader.Obj");

        //访问子类的静态变量，会导致父类初始化
        //System.out.println(Child.age);

        //----------------------------------------------------------

        //子类访问父类的静态变量，子类是不会初始化的,这个属于被动使用的情况
        //System.out.println(Child.getSalary());

        //定义一个类型数组，不会初始化类型
        //Obj[] arr = new Obj[10];

        //引用了类的常量，不会初始化类，因为走的是常量池
        //System.out.println(Obj.finalsalary);

        //是常量，但是值不能提前算出来
        System.out.println(Obj.finalsalary2);
    }
}

interface I{
    int i = 10;
}

class Obj{

    public static int salary = 100000;

    public static final int finalsalary = 100000;

    public static final int finalsalary2 = new Random().nextInt(100);

    static {
        System.out.println("初始化了这个类");
    }

    static int getSalary(){
        return salary;
    }
}

class Child extends Obj{

    public static int age = 32;

    static {
        System.out.println("child 初始化了");
    }
}