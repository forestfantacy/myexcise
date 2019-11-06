package basic.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @auther wei.wang09
 * @date 2019/10/6
 */
public class ProxySubject implements InvocationHandler {

    private Object target;

    public ProxySubject(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //大部分场景跟proxy（代理对象）没啥关系

        System.out.println("before call "+ method);

        //真实对象的 method 方法，传入 args 参数
        Object invoke = method.invoke(target, args);

        System.out.println("after call "+ method);

        return invoke;
    }
}
