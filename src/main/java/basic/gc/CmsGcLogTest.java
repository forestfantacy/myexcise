package basic.gc;

/**
 * @auther wei.wang09
 * @date 2019/10/11
 */
public class CmsGcLogTest {
    //-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC
    //
    public static void main(String[] args) {

        int size = 1024 * 1024;

        byte[] myAlloc1 = new byte[4 * size];
        System.out.println("1111111");

        byte[] myAlloc2 = new byte[4 * size];
        System.out.println("2222222");

        byte[] myAlloc3 = new byte[4 * size];
        System.out.println("3333333");

        byte[] myAlloc4 = new byte[2 * size];
        System.out.println("4444444");

        byte[] myAlloc5 = new byte[1  * size];
        System.out.println("5555555");
    }
}
