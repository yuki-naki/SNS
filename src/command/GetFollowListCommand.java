package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.FollowDao;

public class GetFollowListCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext responseContext){
		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		FollowDao followDao = factory.getFollowDao();

		//SessionからUser情報取得
		RequestContext rc = getRequestContext();
		HttpServletRequest request = (HttpServletRequest)rc.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userId = user.getUserId();

		responseContext.setTarget("followList");
		responseContext.setResult((Object)followDao.getFollowList(userId));

		return responseContext;
	}
}
