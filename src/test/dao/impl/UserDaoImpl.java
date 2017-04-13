package test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;

import test.dao.UserDao;
import test.entity.User;
import test.utils.DBConnection;

import com.google.inject.Singleton;


@Singleton
public class UserDaoImpl implements UserDao{

	@Override
	public void add(String name,int userId) throws SQLException {
		System.out.println("userDaoImpl.....name="+name+" userId="+userId);
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = DBConnection.getConn();
			String sql = "insert into users(account,user_id) values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, userId);
			int rows = ps.executeUpdate();
			System.out.println("受影响的行数："+rows);
		}finally{
			DBConnection.closeAll(conn, ps, null);
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
			conn = DBConnection.getConn();
			String sql = "select * from users";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setAccount(rs.getString("account"));
				u.setUser_id(rs.getInt("user_id"));
				ulist.add(u);
			}
		}finally{
			System.out.println(ulist);
			DBConnection.closeAll(conn, ps, rs);
		}
		return ulist;
	}

}
