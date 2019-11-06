package basic.bytecode.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @auther wei.wang09
 * @date 2019/10/11
 */
public class Client {

    public static void main(String[] args) {

        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        RealSubject rs = new RealSubject();
        InvocationHandler handler = new DynamicSubject(rs);

        Class<? extends RealSubject> rsClass = rs.getClass();

        Subject dynamicSubject = (Subject) Proxy.newProxyInstance(rsClass.getClassLoader(), rsClass.getInterfaces(), handler);

        dynamicSubject.request();
    }
}
