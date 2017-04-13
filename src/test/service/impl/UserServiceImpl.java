package test.service.impl;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import test.dao.UserDao;
import test.entity.User;
import test.service.UserService;

@Singleton
public class UserServiceImpl implements UserService{
	
	@Inject
	private UserDao userDao;

	@Override
	public void add(User u) throws Exception {
		System.out.println("UserServiceImpl..."+u);
		userDao.add(u.getAccount(), u.getUser_id());
		
	}

	@Override
	public void addTwoUser(List<User> ulist) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> find() throws Exception {
		
		return userDao.query();
	}

}
