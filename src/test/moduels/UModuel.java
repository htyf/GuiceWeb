package test.moduels;

import test.dao.UserDao;
import test.dao.impl.UserDaoImpl;
import test.service.UserService;
import test.service.impl.UserServiceImpl;

import com.google.inject.AbstractModule;

public class UModuel extends AbstractModule{

	@Override
	protected void configure() {
		bind(UserDao.class).to(UserDaoImpl.class);
		bind(UserService.class).to(UserServiceImpl.class);
	}

}
