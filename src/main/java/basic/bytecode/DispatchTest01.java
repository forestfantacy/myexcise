package basic.bytecode;

/**
 *
 *
 *
 * @auther wei.wang09
 * @date 2019/10/11
 */
public class DispatchTest01 {

    public void test(Grapa grapa){
        System.out.println("Grapa");
    }

    public void test(Father father){
        System.out.println("Father");
    }

    public void test(Son son){
        System.out.println("Son");
    }

    public static void main(String[] args) {
        Grapa g1 = new Father();
        Grapa g2 = new Son();

        DispatchTest01 dispatchTest01 = new DispatchTest01();
        /**
         *
         * 方法重载，静态分派，g1 g2 都是Grapa，所以会去找Grapa参数方法
         */

        dispatchTest01.test(g1);
        dispatchTest01.test(g2);
    }
}

class Grapa{}

class Father extends Grapa{}

class Son extends Father{}
