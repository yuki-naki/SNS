package command;

import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.GroupDao;

public class DeleteGroupCommand extends AbstractChatCommand{
	public ResponseContext execute(ResponseContext responseContext){
		RequestContext requestContext = getRequestContext();

		//フォロー解除対象のユーザーIDを取得
		String deleteTargetGroupId = requestContext.getParameter("deleteTargetGroupId")[0];

		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		GroupDao groupDao = factory.getGroupDao();

		groupDao.deleteGroup(deleteTargetGroupId);

		Object[] chatData = getChatData(requestContext);

		responseContext.setResult(chatData);
		responseContext.setTarget("chat");
		return responseContext;
	}
}
