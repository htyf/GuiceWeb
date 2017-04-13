package zyf.moduels;


import zyf.dao.UserDao;
import zyf.dao.impl.UserDaoImpl;
import zyf.service.UserService;
import zyf.service.impl.UserServiceImpl;

import com.google.inject.AbstractModule;

public class UModuel extends AbstractModule{

	@Override
	protected void configure() {
		bind(UserDao.class).to(UserDaoImpl.class);
		bind(UserService.class).to(UserServiceImpl.class);
	}

}
