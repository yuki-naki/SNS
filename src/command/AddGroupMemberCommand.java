package command;

import java.util.ArrayList;
import java.util.Arrays;

import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.GroupMemberDao;
import dao.OracleConnectionManager;

public class AddGroupMemberCommand extends AbstractChatCommand{
	public ResponseContext execute(ResponseContext responseContext){
		RequestContext requestContext = getRequestContext();

		//パラメータ取得
		String groupId = requestContext.getParameter("addMemberGroupId")[0];
		String[] selectedUsers = (requestContext.getParameter("selectedUser"));
		ArrayList<String> selectedUsersList = new ArrayList<String>(Arrays.asList(selectedUsers));

		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		GroupMemberDao groupMemberDao = factory.getGroupMemberDao();

		OracleConnectionManager.getInstance().beginTransaction();

		//グループにメンバーを追加する処理
		groupMemberDao.addGroupMember(groupId, selectedUsersList);

		OracleConnectionManager.getInstance().closeConnection();

		Object[] chatData = getChatData(requestContext);
		chatData[0] = groupId;
		responseContext.setResult(chatData);
		responseContext.setTarget("chat");
		return responseContext;
	}
}
