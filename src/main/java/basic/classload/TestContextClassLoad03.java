package basic.classload;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @auther wei.wang09
 * @date 2019/10/10
 */
public class TestContextClassLoad03 {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //加载Driver.class,初始化Driver自身的静态代码块 和父类NonRegisteringDriver
        //完成了注册，registerProvider，这个设计和Cat中的接口注册异曲同工
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("");
    }
}
