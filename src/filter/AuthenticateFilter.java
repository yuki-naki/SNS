package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.User;

public class AuthenticateFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) req).getSession();
		User user = (User) session.getAttribute("user");
		Boolean notChecked = (Boolean) req.getAttribute("notChecked");

		if(user == null || (notChecked != null && notChecked)){
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
			dispatcher.forward(req, resp);
		}
		else {
			chain.doFilter(req, resp);
		}
	}

	public void init(FilterConfig config) throws ServletException {}
	public void destroy() {}
}
