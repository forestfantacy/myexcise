package basic.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @auther wei.wang09
 * @date 2019/10/6
 */
public class Client {


    public static void main(String[] args) {


        Subject realSubject = new RealSubject();

        InvocationHandler handler = new ProxySubject(realSubject);

        ClassLoader classLoader = handler.getClass().getClassLoader();

        Class<?>[] interfaces = realSubject.getClass().getInterfaces();

        /**
         * 创建动态代理的类和对象
         * 需要指定，有哪个加载器加载这个类，代理类需要实现哪些接口，调用处理器
         */
        Subject dynamicSubject = (Subject) Proxy.newProxyInstance(classLoader, interfaces, handler);

        //动态代理对象的方法被调用，流程将转到invocation handler
        dynamicSubject.request();
        String wangwei_proxy = dynamicSubject.request("wangwei proxy");
        System.out.println("call request return ==>"+wangwei_proxy);
    }
}
