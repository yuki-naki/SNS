package command;

import javax.servlet.http.HttpServletRequest;

import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.FollowDao;

public class FollowCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext responseContext){
		RequestContext requestContext = getRequestContext();
		HttpServletRequest request = (HttpServletRequest)requestContext.getRequest();
		//パラメータ取得
		String followerUserId =	 request.getSession().getAttribute("loginUserId").toString();
		String followedUserId = (requestContext.getParameter("userId"))[0];

		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		FollowDao followDao = factory.getFollowDao();

		followDao.follow(followerUserId, followedUserId);

		responseContext.setTarget("top");

		return responseContext;
	}
}
