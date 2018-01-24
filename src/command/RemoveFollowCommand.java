package command;

import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.RemoveFollowDao;

public class RemoveFollowCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext responseContext){
		RequestContext requestContext = getRequestContext();

		//フォロー解除対象のユーザーIDを取得
		String removeTarget = requestContext.getParameter(key);

		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		RemoveFollowDao removeFollowDao = factory.getRemoveFollowDao();
		GetFollowListDao getFollowListDao = factory.getGetFollowListDao();

		removeFollowDao.removeFollow(removeTarget);
		responseContext.setTarget("followList");
		responseContext.setResult((Object)getFollowListDao.getFollowList());
		return responseContext;
	}
}
