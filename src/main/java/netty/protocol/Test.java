package netty.protocol;

/**
 * @auther wei.wang09
 * @date 2019/10/4
 */
public class Test {

    public static void main(String[] args) {

        String str = "456";

        System.out.println(str.hashCode());
        Point point = new Point();
        point.change(str);


        //System.out.println(str);
        //System.out.println(point.x);

    }


    public static class Point{

        private String x = "5";

        public void change(String str) {
            System.out.println(str.hashCode());
            str = "123";
            this.x = "55";
        }
    }
}
