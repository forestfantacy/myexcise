package basic.gc;

/**
 * @auther wei.wang09
 * @date 2019/10/11
 */
public class GcTest01 {
    //Parellel Scavenge
    //-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
    public static void main(String[] args) {

        int size = 1024 * 1024;

        byte[] myAlloc1 = new byte[3 * size];
        byte[] myAlloc2 = new byte[3 * size];
        byte[] myAlloc3 = new byte[3 * size];
        byte[] myAlloc4 = new byte[3 * size];

        System.out.println("hello world");
    }
}
