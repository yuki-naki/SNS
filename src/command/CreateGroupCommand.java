package command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.Chat;
import bean.Group;
import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.ChatDao;
import dao.GroupDao;
import dao.GroupMemberDao;
import dao.OracleConnectionManager;

public class CreateGroupCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext responseContext){

		RequestContext rc = getRequestContext();
		HttpServletRequest request = (HttpServletRequest)rc.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userId = user.getUserId();

		//パラメータ取得
		String[] selectedUsers = (rc.getParameter("selectedUser"));
		ArrayList<String> selectedUsersList = new ArrayList<String>(Arrays.asList(selectedUsers));
		selectedUsersList.add(userId);

		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		GroupDao groupDao = factory.getGroupDao();
		GroupMemberDao groupMemberDao = factory.getGroupMemberDao();
		ChatDao chatDao = factory.getChatDao();

		//新規グループ作成処理
		Group group = new Group();
		group.setGroupName(rc.getParameter("groupName")[0]);
		String groupId = groupDao.createGroup(group);

		//新規グループにメンバーを追加
		groupMemberDao.addGroupMember(groupId, selectedUsersList);

		// chatページに遷移するための処理
		String userString = rc.convertObjectToString(user);
		Map<String, Chat> chats = chatDao.getAllChats(userId);
		OracleConnectionManager.getInstance().closeConnection();

		Object[] array = new Object[3];
		array[0] = groupId;
		array[1] = chats;
		array[2] = userString;

		responseContext.setResult(array);
		responseContext.setTarget("chat");
		return responseContext;
	}
}
