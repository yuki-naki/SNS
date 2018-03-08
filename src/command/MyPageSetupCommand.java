package command;

import java.util.ArrayList;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.OraUserDao;

public class MyPageSetupCommand extends AbstractCommand{

	public ResponseContext execute(ResponseContext resc) {

		RequestContext rc = getRequestContext();

		ArrayList list = null;
		ArrayList userIdList = new ArrayList();

		String user_introduction = rc.getParameter("comment")[0];

		User user = (User)rc.getSessionObject("user");

		OraUserDao dao = new OraUserDao();

		user.setUserIntroduction(user_introduction);

		dao.addMyProfile(user);
		userIdList.add(user.getUserId());

		list = (ArrayList)dao.getUserList(userIdList);

		resc.setResult(list);
		resc.setTarget("myPage");
		return resc;
	}

}
