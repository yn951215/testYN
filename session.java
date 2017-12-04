package servletTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="session",urlPatterns="/session")
public class TestSession extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获得session
		HttpSession session = req.getSession();
		//设置一个变量用来存储次数
		int count = 0;
		//判断获取到的存储内容是否为空
		if(session.getAttribute("count") == null){
			count++;
		}else{
			 count = (int)session.getAttribute("count");
			count++;
		}
		//设置日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTimes = sdf.format(session.getCreationTime());
		String lastTimes = sdf.format(session.getLastAccessedTime());
		
		//设置存储内容
		session.setAttribute("count", count);
		//显示浏览次数
		resp.getWriter().println("visit times:" +count);
		//显示第一次创建的时间
		resp.getWriter().println("created times: "+createTimes);
		//显示最后一次访问的时间
		resp.getWriter().println("last times: "+lastTimes);
		//显示是否为新创建的session 
		resp.getWriter().println("is new: "+session.isNew());
		//显示id
		resp.getWriter().println("id: "+session.getId());
	}
}
