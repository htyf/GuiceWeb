package zyf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;

import zyf.dao.UserDao;
import zyf.entity.User;
import zyf.utils.JdbcUtils;

import com.google.inject.Singleton;


@Singleton
public class UserDaoImpl implements UserDao{

	@Override
	public void add(String name,int userId) throws SQLException {
		System.out.println("userDaoImpl.....name="+name+" userId="+userId);
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = JdbcUtils.getConn();
			String sql = "insert into users(account,user_id) values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, userId);
			int rows = ps.executeUpdate();
			System.out.println("受影响的行数："+rows);
		}finally{
			JdbcUtils.close();
		}
	}

	@Override
	public void addTest() throws SQLException {
		System.out.println("userDaoImplTest.....");

	}

	@Override
	public List<User> query() throws SQLException {
		System.out.println("userDaoImplquery.....");
		List<User> ulist = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtils.getConn();
			String sql = "select * from users order by user_id";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setAccount(rs.getString("account"));
				u.setUser_id(rs.getInt("user_id"));
				ulist.add(u);
			}
		}catch(Exception e){
			System.out.println("异常");
			e.printStackTrace();
		}
		finally{
			System.out.println(ulist);
			JdbcUtils.close();
		}
		return ulist;
	}

	@Override
	public List<User> queryByPage(int pageNum) throws SQLException {
		System.out.println("userDaoImplqueryByPage.....");
		List<User> ulist = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtils.getConn();
			String sql = "select * from users order by user_id limit ?,? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (pageNum-1)*User.PAGE_SIZE);
			ps.setInt(2, User.PAGE_SIZE);
			rs = ps.executeQuery();
			while(rs.next()){
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setAccount(rs.getString("account"));
				u.setUser_id(rs.getInt("user_id"));
				ulist.add(u);
			}
		}catch(Exception e){
			System.out.println("异常");
			e.printStackTrace();
		}
		finally{
			System.out.println(ulist);
			JdbcUtils.close();
		}
		return ulist;
	
	}

	@Override
	public int count() throws SQLException {
		System.out.println("userDaoImplqueryCount.....");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn = JdbcUtils.getConn();
			String sql = "select count(1) from users";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
			}
		}finally{
			System.out.println(count);
			JdbcUtils.close();
		}
		return count;
	
	}

	
}
