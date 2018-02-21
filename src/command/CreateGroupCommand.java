package command;

import java.util.ArrayList;
import java.util.Arrays;

import bean.Group;
import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.GroupDao;
import dao.GroupMemberDao;

public class CreateGroupCommand extends AbstractChatCommand{
	public ResponseContext execute(ResponseContext responseContext){
		RequestContext requestContext = getRequestContext();

		// ログインユーザー情報取得
		User loginUser = (User)requestContext.getSessionObject("user");
		String loginUserId = loginUser.getUserId();

		// パラメータ取得
		String[] selectedUsers = requestContext.getParameter("selectedUser");
		ArrayList<String> selectedUsersList = new ArrayList<String>(Arrays.asList(selectedUsers));
		selectedUsersList.add(loginUserId);

		// dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		GroupDao groupDao = factory.getGroupDao();
		GroupMemberDao groupMemberDao = factory.getGroupMemberDao();;

		// 新規グループ作成処理
		Group group = new Group();
		group.setGroupName(requestContext.getParameter("groupName")[0]);
		String groupId = groupDao.createGroup(group);

		// 新規グループにメンバーを追加
		groupMemberDao.addGroupMember(groupId, selectedUsersList);

		Object[] chatData = getChatData(requestContext);

		responseContext.setResult(chatData);
		responseContext.setTarget("chat");
		return responseContext;
	}
}
