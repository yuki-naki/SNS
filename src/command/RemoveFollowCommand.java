package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.FollowDao;

public class RemoveFollowCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext responseContext){

		RequestContext rc = getRequestContext();
		HttpServletRequest request = (HttpServletRequest)rc.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userId = user.getUserId();

		//フォロー解除対象のユーザーIDを取得
		String removeTargetUserId = rc.getParameter("removeTargetUserId")[0];

		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		FollowDao FollowDao = factory.getFollowDao();

		FollowDao.removeFollow(userId, removeTargetUserId);
		responseContext.setTarget("followList");
		responseContext.setResult((Object)FollowDao.getFollowList(userId));
		return responseContext;
	}
}
