package basic.classload;

/**
 * @auther wei.wang09
 * @date 2019/10/9
 */
public class TestFinal01 {

    public static void main(String[] args) {
        System.out.println(Final0.s);
    }
}

class Final0{
    public static final int s = 3;
    static {
        System.out.println("Final0 stack block");
    }
}
