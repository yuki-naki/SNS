package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.FollowDao;

public class GetUnFollowListCommand extends AbstractCommand{
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
		//useridの取得は問題なし

		responseContext.setTarget("unFollowList");
		responseContext.setResult((Object)followDao.getUnFollowList(userId));

		return responseContext;
	}
}
