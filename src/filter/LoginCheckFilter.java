package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.AbstractDaoFactory;
import dao.OracleConnectionManager;
import dao.UserDao;

public class LoginCheckFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		String logout = req.getParameter("logout");
		if(logout != null){
			HttpSession session = ((HttpServletRequest)req).getSession();
			session.invalidate();
			chain.doFilter(req, resp);
		}
		else {
			String paramId = req.getParameter("id");
			String paramPass = req.getParameter("password");

			AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
			UserDao userDao = factory.getUserDao();
			User user = userDao.getUser(paramId, paramPass);

			OracleConnectionManager.getInstance().closeConnection();

			if(user != null){

				String id = user.getLoginId();
				String password = user.getPassword();

				if(paramId.equals(id) && paramPass.equals(password)){
					HttpSession session = ((HttpServletRequest)req).getSession();
					session.setAttribute("user", user);
				}
			}
			req.setAttribute("notChecked", true);
			req.setAttribute("paramId", paramId);
			chain.doFilter(req, resp);
		}
	}

	public void init(FilterConfig config) throws ServletException {}

	public void destroy() {}
}
