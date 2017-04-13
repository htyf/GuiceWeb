package test.dao;

import java.sql.SQLException;
import java.util.List;



import test.entity.User;

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
	
	List<User> query() throws SQLException;

}
