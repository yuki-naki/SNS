package command;

import context.ResponseContext;
import dao.AbstractDaoFactory;

public class GetFollowListCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext responseContext){
		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		GetFollowListDao getFollowListDao = factory.getGetFollowListDao();

		responseContext.setTarget("followList");
		responseContext.setResult((Object)getFollowListDao.getFollowList());
		return responseContext;
	}
}
