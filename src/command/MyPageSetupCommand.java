package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.MyProfileDao;

public class MyPageSetupCommand extends AbstractCommand{

	public ResponseContext execute(ResponseContext resc) {

		System.out.println("mypagesetupo開始");

		RequestContext rc = getRequestContext();
		HttpServletRequest hreq = (HttpServletRequest)rc.getRequest();
		HttpSession session = hreq.getSession();

		User user = (User)session.getAttribute("user");




		MyProfileDao dao = new MyProfileDao();

		user = dao.getMyProfile(user);

		System.out.println(user.getUsername());

		resc.setResult(user);
		resc.setTarget("myPage");
		return resc;
	}

}
