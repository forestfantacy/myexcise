package basic.classload;

/**
 * @auther wei.wang09
 * @date 2019/10/9
 */
public class TestClassLoad05 {

    public static void main(String[] args) {


        /**
         * 元素被哪个cl加载，数组跟着走
         */
        String[] arr = new String[2];
        System.out.println(arr.getClass().getClassLoader());//null

        B[] bs = new B[2];
        System.out.println(bs.getClass().getClassLoader());

        int[] tt = new int[2];
        System.out.println(tt.getClass().getClassLoader());//null 原生类型没有cl
    }
}

class B{}
