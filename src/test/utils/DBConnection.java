package test.utils;

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

public class DBConnection {
	private static String username ;
	private static String url;
	private static String driver;
	private static String password;
	static{
		InputStream is  = DBConnection.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			Properties p = new Properties();
			p.load(is);
			 username = p.getProperty("username");
			 url = p.getProperty("url");
			 driver = p.getProperty("driver");
			 password = p.getProperty("password");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("文件路径不存在");
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConn(){
		Connection conn=null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 关闭连接
	 * @param conn
	 * @param st
	 * @param rs
	 * @throws SQLException
	 */
	public static void closeAll(Connection conn,Statement st,ResultSet rs) {
		try{
			if(conn!=null){
				conn.close();
			}
			if(st!=null){
				st.close();
			}
			if(rs!=null){
				rs.close();
			}
		}catch(SQLException e){
			System.out.println("关闭资源出现异常");
			e.printStackTrace();
		}
		
	}
}
