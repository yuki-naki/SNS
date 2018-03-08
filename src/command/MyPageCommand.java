package command;

import java.util.ArrayList;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.OraUserDao;

public class MyPageCommand extends AbstractCommand{

	public ResponseContext execute(ResponseContext resc) {


		RequestContext rc = getRequestContext();

		ArrayList list = null;
		ArrayList userIdList = new ArrayList();

		User user = (User)rc.getSessionObject("user");

		userIdList.add(user.getUserId());

		OraUserDao dao = new OraUserDao();

		list = (ArrayList)dao.getUserList(userIdList);


		resc.setResult(list);
		resc.setTarget("myPage");
		return resc;
	}

}
