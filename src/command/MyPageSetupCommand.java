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



		dao.addMyProfile(user.getUserId(),user_introduction);

		list = (ArrayList)dao.getMyProfile(user.getUserId());

		System.out.println(user.getUsername());
		System.out.println(user_introduction);

		resc.setResult(list);
		resc.setTarget("myPage");
		return resc;
	}

}
