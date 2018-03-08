package command;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import bean.Group;
import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.GroupDao;
import dao.GroupMemberDao;
import dao.OracleConnectionManager;

public class CreateGroupCommand extends AbstractChatCommand{
	public ResponseContext execute(ResponseContext responseContext){
		RequestContext requestContext = getRequestContext();
		FileInputStream inputStream = null;
		String groupId = null;

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

		OracleConnectionManager.getInstance().beginTransaction();

		// 新規グループ作成処理
		Group group = new Group();
		group.setGroupName(requestContext.getParameter("groupName")[0]);
		File file = new File("C:/workspace/SNS/WebContent/img/group_default.jpg");
		try{
			inputStream = new FileInputStream(file);
			groupId = groupDao.createGroup(group, inputStream, (int)file.length());

			// 新規グループにメンバーを追加
			groupMemberDao.addGroupMember(groupId, selectedUsersList);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(inputStream != null){
				try{
					inputStream.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}

		OracleConnectionManager.getInstance().closeConnection();

		Object[] chatData = getChatData(requestContext);
		chatData[0] = groupId;

		responseContext.setResult(chatData);
		responseContext.setTarget("chat");
		return responseContext;
	}
}
