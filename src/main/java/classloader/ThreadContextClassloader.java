package classloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-01-31 10:04
 */
public class ThreadContextClassloader {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        //
        //System.out.println("===>" + contextClassLoader.hashCode());
        //System.out.println("===>" + contextClassLoader.getParent().hashCode());
        //
        //new Thread(()->{
        //    ClassLoader contextClassLoader4SubThread = Thread.currentThread().getContextClassLoader();
        //    System.out.println(contextClassLoader4SubThread.hashCode());
        //    System.out.println(contextClassLoader4SubThread.getParent().hashCode());
        //}).start();
        //
        //Thread.currentThread().setContextClassLoader(new MyClassLoader());
        //System.out.println("@@@>" + Thread.currentThread().getContextClassLoader());


        //是clinit初始化driver的静态代码块,执行注册driver实例的逻辑
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");

        //这里有核心逻辑：找到可以加载到驱动包的类加载器，先找调用者的类的加载器，没有的话后找当前线程上下文加载器
        //然后用class.forname(drivename , classloadname)找
        Connection connection = DriverManager.getConnection("");

        /**
         *
         *
         *
         *  遍历所有注册的driver，找到哪些可以被上下文加载器加载
         for(DriverInfo aDriver : registeredDrivers) {
             // If the caller does not have permission to load the driver then
             // skip it.
             if(isDriverAllowed(aDriver.driver, callerCL)) {
             try {
                 println("    trying " + aDriver.driver.getClass().getName());
                 Connection con = aDriver.driver.connect(url, info);
                 if (con != null) {
                 // Success!
                 println("getConnection returning " + aDriver.driver.getClass().getName());
                 return (con);
             }
             } catch (SQLException ex) {
                 if (reason == null) {
                    reason = ex;
                 }
             }

             } else {
             println("    skipping: " + aDriver.getClass().getName());
             }

         }


         private static boolean isDriverAllowed(Driver driver, ClassLoader classLoader) {
             boolean result = false;
             if(driver != null) {
                 Class<?> aClass = null;
                 try {
                    aClass =  Class.forName(driver.getClass().getName(), true, classLoader);
                 } catch (Exception ex) {
                    result = false;
                 }

                 result = ( aClass == driver.getClass() ) ? true : false;
             }

             return result;
         }

         */

    }




}
