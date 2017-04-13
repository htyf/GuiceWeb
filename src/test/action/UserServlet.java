package test.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import test.entity.User;
import test.service.UserService;

@Singleton
public class UserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Inject
	private UserService userService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String account = req.getParameter("account");
		String userid = req.getParameter("userid");
		int userId = 0;
		if(userid!=null){
			userId = Integer.valueOf(userid);
		}
		try {
			if(account==null&&userid==null){
				List<User> find = userService.find();
				
				resp.getWriter().write(find.toString());
				resp.getWriter().flush();
				resp.getWriter().close();
			}else{
				User u = new User();
				u.setAccount(account);
				u.setUser_id(userId);
				List<User> ulist=new ArrayList<>();
				ulist.add(u);

				userService.add(u);
				resp.getWriter().write("添加成功");
				resp.getWriter().flush();
				resp.getWriter().close();
			}
			System.out.println("ok");

		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
			// 注意：调用service层的方法出异常之后，继续将异常抛出，这样在TransactionFilter就能捕获到抛出的异常，继而执行事务回滚操作
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws Exception {
		UserServlet us = Guice.createInjector().getInstance(UserServlet.class);
		//		User u = new User("12",4);
		us.userService.find();
	}


}
