package zyf.moduels;


import zyf.action.UserServlet;
import zyf.filter.EncodingFilter;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.ServletModule;

public class UserModuel extends AbstractModule{

	@Override
	protected void configure() {
//		//关系映射
//		install(new UModuel());
		//地址映射
		install(new ServletModule(){
			@Override
			protected void configureServlets() {
//				serve("/userServlet").with(UserServlet.class);
				//一个servlet拥有多个访问地址
				serve("/u","/user1").with(UserServlet.class);
				//filter配置与servlet类似 ，也可配置多个地址
				filter("/*").through(EncodingFilter.class);
			}
		});
		
		
	}

}
