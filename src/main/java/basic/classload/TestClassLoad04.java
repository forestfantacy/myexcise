package basic.classload;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @auther wei.wang09
 * @date 2019/10/9
 */
public class TestClassLoad04 {


    public static void main(String[] args) throws IOException {
        /**
         *
         * 返回线程调用者所在的类加载器
         *
         */
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);

        /**
         * 类加载器加载到的资源列表
         */
        String resource = "basic/classload/TestClassLoad03.class";//包路径+x.class
        //file:/Users/forest/workbench/myrepo/myguava/target/classes/basic/classload/TestClassLoad03.class

        Enumeration<URL> urls = contextClassLoader.getResources(resource);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url);
        }

        System.out.println("----------");

        /**
         * 获取当前类的classloader
         */
        ClassLoader classLoader = TestClassLoad04.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(String.class.getClassLoader());
    }
}
