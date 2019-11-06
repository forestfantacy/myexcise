package basic.classload;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @auther wei.wang09
 * @date 2019/10/10
 */
public class TestContextClassLoad02 {

    public static void main(String[] args) {
        //改成扩展类加载器，那么ServiceLoader load方法将找不到driver，因为他在ext.dir找不到Driver
        //Thread.currentThread().setContextClassLoader(TestContextClassLoad02.class.getClassLoader().getParent());


        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);//ServiceLoader是被root加载器加载的，load方法里面使用的类会被root加载
        for (Iterator<Driver> iterator = serviceLoader.iterator();iterator.hasNext();) {
            Driver driver = iterator.next();
            System.out.println("drive:" + driver.getClass() + ",classload:" + driver.getClass().getClassLoader());//app
        }

        System.out.println("当前上下文线程类加载器：" + Thread.currentThread().getContextClassLoader());//app
        System.out.println("ServiceLoader 的类加载器" + ServiceLoader.class.getClassLoader());//null
    }
}
