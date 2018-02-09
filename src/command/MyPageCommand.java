package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.MyProfileDao;

public class MyPageCommand extends AbstractCommand{

	public ResponseContext execute(ResponseContext resc) {

		System.out.println("mypage開k始");

		RequestContext rc = getRequestContext();
		HttpServletRequest hreq = (HttpServletRequest)rc.getRequest();
		HttpSession session = hreq.getSession();
		ArrayList list = null;

		User user = (User)session.getAttribute("user");





		MyProfileDao dao = new MyProfileDao();

		list = (ArrayList)dao.getMyProfile(user.getUserId());

		System.out.println(user.getUsername());

		resc.setResult(list);
		resc.setTarget("myPage");
		return resc;
	}

}
