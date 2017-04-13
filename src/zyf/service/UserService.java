package zyf.service;

import java.util.List;

import com.google.inject.ImplementedBy;

import zyf.entity.User;
import zyf.service.impl.UserServiceImpl;

//@ImplementedBy(UserServiceImpl.class)
public interface UserService {
	
	void add(User u) throws Exception;

	void addTwoUser(List<User> ulist) throws Exception;
	
	List<User> find() throws Exception;
	
	List<User> findByPage(int pageNum) throws Exception;
	
	int count() throws Exception;
}
