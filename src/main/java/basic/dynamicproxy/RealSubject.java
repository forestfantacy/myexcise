package basic.dynamicproxy;

/**
 * @auther wei.wang09
 * @date 2019/10/6
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("from realsubject");
    }

    @Override
    public String request(String msg) {
        return "hello " + msg;
    }
}
