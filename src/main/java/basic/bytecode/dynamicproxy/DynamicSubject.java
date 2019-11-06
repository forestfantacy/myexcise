package basic.bytecode.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @auther wei.wang09
 * @date 2019/10/11
 */
public class DynamicSubject implements InvocationHandler {

    private Subject realSubject;

    public DynamicSubject(Subject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before realsubject...");
        method.invoke(realSubject, args);
        System.out.println("post realsubject...");

        return null;
    }
}
