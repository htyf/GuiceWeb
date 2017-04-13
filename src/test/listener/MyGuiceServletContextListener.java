package test.listener;

import test.moduels.UModuel;
import test.moduels.UserModuel;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class MyGuiceServletContextListener extends GuiceServletContextListener{

	@Override
	protected Injector getInjector() {
		
//		return Guice.createInjector(new UserModuel());
		//绑定多个moduel
		return Guice.createInjector(new UserModuel(),new UModuel());
	}

}
