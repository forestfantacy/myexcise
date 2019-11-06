package basic.bytecode.dynamicproxy;

/**
 * @auther wei.wang09
 * @date 2019/10/11
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("realsubject...");
    }
}
