package command;

import java.util.ArrayList;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.MyProfileDao;

public class MyPageCommand extends AbstractCommand{

	public ResponseContext execute(ResponseContext resc) {

		System.out.println("mypage開k始");

		RequestContext rc = getRequestContext();

		ArrayList list = null;

		User user = (User)rc.getSessionObject("user");



		MyProfileDao dao = new MyProfileDao();

		list = (ArrayList)dao.getMyProfile(user.getUserId());

		System.out.println(user.getUsername());

		resc.setResult(list);
		resc.setTarget("myPage");
		return resc;
	}

}
