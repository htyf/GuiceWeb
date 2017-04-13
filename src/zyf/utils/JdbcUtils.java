package zyf.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * c3p0连接数据库的工具类
 * @author yanfangzhang
 *
 */
public class JdbcUtils {
	private static String username ;
	private static String url;
	private static String driver;
	private static String password;
	private static ComboPooledDataSource ds = null;
	// 使用ThreadLocal存储当前线程中的Connection对象
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	//获取数据库连接的参数
	static{
		InputStream is  = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			Properties p = new Properties();
			p.load(is);
			username = p.getProperty("username");
			url = p.getProperty("url");
			driver = p.getProperty("driver");
			password = p.getProperty("password");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	//创建数据库连接池
	static{
		try {
			ds = new ComboPooledDataSource();
			ds.setDriverClass(driver);
			ds.setUser(username);
			ds.setPassword(password);
			ds.setJdbcUrl(url);
			ds.setInitialPoolSize(10);
			ds.setMinPoolSize(5);
			ds.setMaxPoolSize(20);

			// 通过读取C3P0的xml配置文件创建数据源，C3P0的xml配置文件c3p0-config.xml必须放在src目录下
			// ds = new ComboPooledDataSource();//使用C3P0的默认配置来创建数据源
			// ds = new ComboPooledDataSource("MySQL");// 使用C3P0的命名配置来创建数据源

		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * 获取数据源
	 * @return
	 */
	public static DataSource getDataSource(){
		return ds;
	}

	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConn() throws SQLException{
		Connection conn = threadLocal.get();
		if(conn==null){
			//从数据源获取数据库连接
			conn=getDataSource().getConnection();
			//将数据库连接绑定到当前线程
			threadLocal.set(conn);
		}
		return conn;
	}

	/**
	 * 开启事务
	 * @throws SQLException
	 */
	public static void startTransaction(){
		try {
			Connection conn = getConn();
			// 开启事务
			conn.setAutoCommit(false);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 提交事务
	 * @throws SQLException
	 */
	public static void commit(){
		try {
			Connection conn = getConn();
			conn.commit();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 回滚事务
	 */
	public static void rollback(){

		try {
			Connection conn = getConn();
			conn.rollback();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	/**
	 * 关闭数据库连接
	 */
	public static void close() {
		try{
			// 从当前线程中获取Connection
			Connection conn = threadLocal.get();
			if(conn!=null){
				conn.close();
				//解除与当前线程的绑定
				threadLocal.remove();
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
