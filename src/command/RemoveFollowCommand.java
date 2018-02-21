package command;

import java.util.List;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.FollowDao;
import dao.UserDao;

public class RemoveFollowCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext responseContext){
		RequestContext rc = getRequestContext();

		//SessionからUser情報取得
		User loginUser = (User)rc.getSessionObject("user");
		String loginUserId = loginUser.getUserId();

		//フォロー解除対象のユーザーIDを取得
		String removeTargetUserId = rc.getParameter("removeTargetUserId")[0];

		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		FollowDao followDao = factory.getFollowDao();
		UserDao userDao = factory.getUserDao();

		followDao.removeFollow(loginUserId, removeTargetUserId);

		List<String> followIdList = followDao.getFollowIdList(loginUserId);
		List<User> followList = userDao.getUserList(followIdList);

		responseContext.setTarget("followList");
		responseContext.setResult(followList);
		return responseContext;
	}
}
