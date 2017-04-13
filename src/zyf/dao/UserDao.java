package zyf.dao;

import java.sql.SQLException;
import java.util.List;

import zyf.entity.User;

import com.google.inject.ImplementedBy;


//@ImplementedBy(UserDaoImpl.class)
public interface UserDao {
	
	/**
	 * 添加用户到数据库
	 * @param user
	 * @throws SQLException
	 */
	void add(String name,int userId) throws SQLException;

	
	void addTest() throws SQLException;
	
	/**
	 * 查询出所有的数据
	 * @return
	 * @throws SQLException
	 */
	List<User> query() throws SQLException;
	
	/**
	 * 分页查询出所有的数据
	 * @param pageNum 当前需要查询的页数
	 * @return
	 * @throws SQLException
	 */
	List<User> queryByPage(int pageNum) throws SQLException;
	/**
	 * 数据库中总共有的记录数
	 * @return
	 * @throws SQLException
	 */
	int count() throws SQLException;

}
