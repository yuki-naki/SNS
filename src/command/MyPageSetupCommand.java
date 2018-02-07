package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.MyPageDao;

public class MyPageSetupCommand extends AbstractCommand{

	public ResponseContext execute(ResponseContext resc) {

		RequestContext rc = getRequestContext();
		HttpServletRequest hreq = (HttpServletRequest)rc.getRequest();
		HttpSession session = hreq.getSession();

		User user = (User)session.getAttribute("user");


		MyPageDao dao = new MyPageDao();

		user = dao.getMyProfile(user);

		System.out.println(user.getUsername());

		resc.setTarget("myPage");
		return resc;
	}

}
