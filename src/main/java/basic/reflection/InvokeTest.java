package basic.reflex;

import java.lang.reflect.Method;

/**
 * @auther wei.wang09
 * @date 2019/10/6
 */
public class InvokeTest {

    public void echo2() {
        System.out.println("InvokeTest echo");
    }

    public int add(int a, int b) {
        return a + b;
    }

    public String echo(String msg) {
        return "hello" + msg;
    }

    public static void main(String[] args) throws Exception {
        Class<?> classType = InvokeTest.class;
        Object invokeTest = classType.newInstance();

        Method addMethod = classType.getMethod("add", new Class[]{int.class,int.class});
        Method echoMethod = classType.getMethod("echo", new Class[]{String.class});
        Object invoke = addMethod.invoke(invokeTest, new Object[]{2, 3});
        System.out.println(invoke);
        Object world = echoMethod.invoke(invokeTest, new Object[]{"world"});
        System.out.println(world);

        Method echoMethod2 = classType.getMethod("echo2", new Class[]{});
        Object invoke1 = echoMethod2.invoke(invokeTest, new Object[]{});

    }


}
