package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {

    /*
    关于静态代码块，要注意的是：
    它是
    随着类的加载而执行，只执行一次，并优先于主函数
    。具体说，
    静态代码块是由类调用
    的。类调用时，先执行静态代码块，然后才执行主函数的。
    静态代码块其实就是给类初始化的，而构造代码块是给对象初始化的。
    静态代码块中的变量是局部变量，与普通函数中的局部变量性质没有区别。
    一个类中可以有多个静态代码块
     */

    /*
    By default, c3p0 will look for an XML configuration file in its classloader's resource path under the name
    "/c3p0-config.xml". That means the XML file should be placed in a directly or jar file directly named in
    your applications CLASSPATH, in WEB-INF/classes, or some similar location. If you prefer not to bundle your
    configuration with your code, you can specify an ordinary filesystem location for c3p0's configuration file via
    the system property com.mchange.v2.c3p0.cfg.xml.
     */

    private static DataSource dataSource = null;
    static {
        //ComboPooledDataSource();方法能够自动读取c3p0中的配置内容生成dataSource类
        dataSource = new ComboPooledDataSource();
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

}
