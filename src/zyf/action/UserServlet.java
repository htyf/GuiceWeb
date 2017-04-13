package zyf.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zyf.entity.User;
import zyf.service.UserService;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Singleton;


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
				//不分页时的情况
//				List<User> find = userService.find();
				//分页
				int currPage = 1;
				String page = req.getParameter("page");
				if(page!=null){
					currPage=Integer.parseInt(page);
				}
				List<User> find = userService.findByPage(currPage);
				//总共的记录条数
				int count = userService.count();
				int pageCount = 0;//总共的页数
				if(count%User.PAGE_SIZE==0){
					pageCount=count/User.PAGE_SIZE;
				}else{
					pageCount = count/User.PAGE_SIZE+1;
				}
				StringBuffer sb = buildPageBar(currPage, pageCount,count);
				req.setAttribute("list", find);
				req.setAttribute("pageBar", sb);
				req.getRequestDispatcher("/u_list.jsp").forward(req, resp);
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

	/**
	 * 构建页码条
	 * @param currPage
	 * @param pageCount
	 * @return
	 */
	public StringBuffer buildPageBar(int currPage,int pageCount,int count){
		StringBuffer sb = new StringBuffer();
		if(count>0){
			if(currPage<=1){
				sb.append("<a href='u?page="+currPage+"'>上一页</a>");
			}else{
				sb.append("<a href='u?page="+(currPage-1)+"'>上一页</a>");
			}
			sb.append(" ");
			//通过循环构建页码条
			for(int i=1;i<=pageCount;i++){
				if(i==currPage){
					sb.append("["+i+"]");
				}else{
					sb.append("<a href='u?page="+i+"'>"+i+"</a>");
				}
				sb.append(" ");
			}
			if(currPage>=pageCount){
				sb.append("<a href='u?page="+pageCount+"'>下一页</a>");
			}else{
				sb.append("<a href='u?page="+(currPage+1)+"'>下一页</a>");
			}
			sb.append(" ");
			sb.append("总共有"+count+"条记录");
		}else{
			sb.append("没有记录");
		}
		
		
		return sb;
	}
	
	
	public static void main(String[] args) throws Exception {
		UserServlet us = Guice.createInjector().getInstance(UserServlet.class);
		//		User u = new User("12",4);
		us.userService.find();
	}


}
