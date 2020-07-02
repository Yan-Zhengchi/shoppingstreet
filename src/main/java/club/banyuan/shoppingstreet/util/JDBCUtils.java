package club.banyuan.shoppingstreet.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    //定义连接数据库的参数变量
    private static String driver;
    private static String url;
    private static String username;
    private static String password;


    //初始化链接数据库的参数变量
    static {
        //创建properties对象，读取配置文件中的键值对
        Properties properties = new Properties();
        //读取配置文件
        InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            //properties对象加载配置文件
            properties.load(resourceAsStream);
            //获取properties文件中的值初始化为数据库参数变量
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取链接对象的方法
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //加载数据库驱动，可省略
        Class.forName(driver);
        //获取数据库链接对象
        Connection connection = DriverManager.getConnection(url, username, password);
        //返回数据库连接对象
        return connection;
    }

    //不带结果集的关闭链接方法
    public static void close(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
    public static void close(Connection conn, Statement statement) throws SQLException {
        if (conn != null) {
            conn.close();
        }
        if (statement != null) {
            statement.close();
        }
    }

    //带结果集的关闭连接方法
    public static  void close(Connection conn, Statement statement, ResultSet rs) throws SQLException {

        if (conn != null) {
            conn.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (rs != null) {
            rs.close();
        }
    }
}
