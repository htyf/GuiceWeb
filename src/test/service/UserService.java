package test.service;

import java.util.List;

import com.google.inject.ImplementedBy;

import test.entity.User;
import test.service.impl.UserServiceImpl;

//@ImplementedBy(UserServiceImpl.class)
public interface UserService {
	
	void add(User u) throws Exception;

	void addTwoUser(List<User> ulist) throws Exception;
	
	List<User> find() throws Exception;
}
