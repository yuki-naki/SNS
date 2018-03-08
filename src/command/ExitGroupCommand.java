package command;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.GroupMemberDao;
import dao.OracleConnectionManager;

public class ExitGroupCommand extends AbstractChatCommand{
	public ResponseContext execute(ResponseContext responseContext) {
		RequestContext requestContext = getRequestContext();

		//パラメータ取得
		String groupId = requestContext.getParameter("exitGroupId")[0];

		// ログインユーザー情報取得
		User loginUser = (User)requestContext.getSessionObject("user");
		String loginUserId = loginUser.getUserId();

		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		GroupMemberDao groupMemberDao = factory.getGroupMemberDao();

		OracleConnectionManager.getInstance().beginTransaction();

		//グループを抜ける処理
		groupMemberDao.exitGroup(groupId, loginUserId);

		OracleConnectionManager.getInstance().closeConnection();

		Object[] chatData = getChatData(requestContext);
		chatData[0] = groupId;

		responseContext.setResult(chatData);
		responseContext.setTarget("chat");
		return responseContext;
	}
}
