package command;

import java.util.List;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.FollowDao;
import dao.OracleConnectionManager;
import dao.UserDao;

public class GetFollowListCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext responseContext){
		RequestContext rc = getRequestContext();

		//SessionからUser情報取得
		User loginUser = (User)rc.getSessionObject("user");
		String loginUserId = loginUser.getUserId();

		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		FollowDao followDao = factory.getFollowDao();
		UserDao userDao = factory.getUserDao();

		OracleConnectionManager.getInstance().beginTransaction();

		List<String> followIdList = followDao.getFollowIdList(loginUserId);
		List<User> followList = userDao.getUserList(followIdList);

		OracleConnectionManager.getInstance().closeConnection();

		responseContext.setTarget("followList");
		responseContext.setResult(followList);

		return responseContext;
	}
}
