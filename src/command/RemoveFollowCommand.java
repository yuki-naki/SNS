package command;

import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.FollowsListDao;
import dao.RemoveFollowDao;

public class RemoveFollowCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext responseContext){
		RequestContext requestContext = getRequestContext();

		//フォロー解除対象のユーザーIDを取得
		String removeTarget = requestContext.getParameter("key")[0];

		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		RemoveFollowDao removeFollowDao = factory.getRemoveFollowDao();
		FollowsListDao followsListDao = factory.getFollowsListDao();

		removeFollowDao.removeFollow(removeTarget);
		responseContext.setTarget("followList");
		responseContext.setResult((Object)followsListDao.getFollowsList("user_id"));
		return responseContext;
	}
}
