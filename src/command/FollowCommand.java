package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.FollowDao;

public class FollowCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext responseContext){

		RequestContext rc = getRequestContext();
		HttpServletRequest request = (HttpServletRequest)rc.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userId = user.getUserId();

		//パラメータ取得
		String followedUserId = (rc.getParameter("userId"))[0];

		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		FollowDao followDao = factory.getFollowDao();

		followDao.follow(userId, followedUserId);

		responseContext.setTarget("unFollowList");
		responseContext.setResult((Object)followDao.getUnFollowList(userId));

		return responseContext;
	}
}
