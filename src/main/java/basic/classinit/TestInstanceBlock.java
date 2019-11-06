package basic.classinit;

/**
 * @auther wei.wang09
 * @date 2019/10/9
 */
public class TestInstanceBlock {
    public static void main(String[] args) {
        new C();
        new C();
    }
}

class C{

    {
        System.out.println("hello");
    }
    //static {
    //    System.out.println("hello");
    //}
    public C(){
        System.out.println("C");
    }
}
