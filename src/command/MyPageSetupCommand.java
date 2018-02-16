package command;

import java.util.ArrayList;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.MyProfileDao;
import dao.OraUserDao;

public class MyPageSetupCommand extends AbstractCommand{

	public ResponseContext execute(ResponseContext resc) {

		RequestContext rc = getRequestContext();

		ArrayList list = null;

		String user_introduction = rc.getParameter("comment")[0];

		User user = (User)rc.getSessionObject("user");
		User user2 = null;

		MyProfileDao dao = new MyProfileDao();
		OraUserDao udao = new OraUserDao();
		user2 = udao.getUserByUserIcon(user.getUserId());

		user.setIcon(user2.getIcon());
		user.setUserIntroduction(user_introduction);



		dao.addMyProfile(user);

		list = (ArrayList)dao.getMyProfile(user.getUserId());

		System.out.println(user.getUsername());
		System.out.println(user_introduction);

		resc.setResult(list);
		resc.setTarget("myPage");
		return resc;
	}

}
