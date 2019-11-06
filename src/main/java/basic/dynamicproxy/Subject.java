package basic.dynamicproxy;

/**
 * @auther wei.wang09
 * @date 2019/10/6
 */
public interface Subject {

    void request();

    String request(String msg);
}
