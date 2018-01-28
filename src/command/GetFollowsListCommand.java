package command;

import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.FollowsListDao;

public class GetFollowsListCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext responseContext){
		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		FollowsListDao followsListDao = factory.getFollowsListDao();

		responseContext.setTarget("followList");
		responseContext.setResult((Object)followsListDao.getFollowsList("user_id"));
		return responseContext;
	}
}
