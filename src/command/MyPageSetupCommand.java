package command;

import java.util.ArrayList;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.MyProfileDao;

public class MyPageSetupCommand extends AbstractCommand{

	public ResponseContext execute(ResponseContext resc) {

		RequestContext rc = getRequestContext();

		ArrayList list = null;

		String user_introduction = rc.getParameter("comment")[0];

		User user = (User)rc.getSessionObject("user");

		MyProfileDao dao = new MyProfileDao();

		user.setUserIntroduction(user_introduction);

		dao.addMyProfile(user);

		list = (ArrayList)dao.getMyProfile(user.getUserId());

		resc.setResult(list);
		resc.setTarget("myPage");
		return resc;
	}

}
