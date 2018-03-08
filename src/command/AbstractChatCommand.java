package command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.Chat;
import bean.Group;
import bean.Message;
import bean.User;
import context.RequestContext;
import dao.AbstractDaoFactory;
import dao.FollowDao;
import dao.GroupDao;
import dao.GroupMemberDao;
import dao.MessageDao;
import dao.OracleConnectionManager;
import dao.UserDao;

public abstract class AbstractChatCommand extends AbstractCommand{
	protected Object[] getChatData(RequestContext requestContext){

		// ログインユーザー情報取得
		User loginUser = (User)requestContext.getSessionObject("user");
		String loginUserId = loginUser.getUserId();
		String userString = requestContext.convertObjectToString(loginUser);

		// メッセージを表示するグループのID取得
		String[] chatIdTab = requestContext.getParameter("chatId");
		String chatId = null;
		if(chatIdTab != null){
			chatId = chatIdTab[0];
		}

		// Dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		GroupMemberDao groupMemberDao = factory.getGroupMemberDao();
		GroupDao groupDao = factory.getGroupDao();
		MessageDao messageDao = factory.getMessageDao();
		FollowDao followDao = factory.getFollowDao();
		UserDao userDao = factory.getUserDao();

		Map<String, Chat> chats = new LinkedHashMap<String, Chat>();

		OracleConnectionManager.getInstance().beginTransaction();

		// 自分が所属しているグループのIDリスト取得
		ArrayList<String> belongGroupIdList = (ArrayList<String>)groupMemberDao.getBelongGroupIdList(loginUserId);

		// 自分を除くすべてのユーザーの情報取得
		ArrayList<User> allUserList = (ArrayList<User>) userDao.getAllUserList(loginUserId);

		// フォローしている人を取得
		List<String> followIdList = (ArrayList<String>)followDao.getFollowIdList(loginUserId);

		// フォローフラグセット
		for(User user : allUserList){
            if(followIdList.contains(user.getUserId())){
            	user.setFollowFlag(true);
            }
        }

		Iterator<String> belongGroupIdListIterator = belongGroupIdList.iterator();

		// 所属しているグループ毎にそれぞれの情報を取得
		while(belongGroupIdListIterator.hasNext()){
			String groupId = belongGroupIdListIterator.next();
			if(groupId == null) break;
			Chat chat = new Chat();

			// 所属group
			Group group = groupDao.getGroup(groupId);

			// 所属member
			List<User> memberList = new ArrayList<User>();
			List<String> memberIdList = (ArrayList<String>)groupMemberDao.getGroupMemberIdList(groupId);
			memberList = userDao.getUserList(memberIdList);

			// 未所属member
			List<User> notMemberList = new ArrayList<User>();
			ArrayList<String> notMemberIdList = (ArrayList<String>)groupMemberDao.getNotGroupMemberIdList(groupId);
			notMemberList = userDao.getUserList(notMemberIdList);
			for(User notMember : notMemberList){
	            if(followIdList.contains(notMember.getUserId())){
	            	notMember.setFollowFlag(true);
	            }
	        }

			// 所属グループのmessage
			List<Message> messageList = (ArrayList<Message>)messageDao.getMessageList(groupId);
			messageList.forEach(m -> m.setUser(userDao.getUserByUserId(m.getUserId())));

			chat.setGroupId(group.getGroupId());
			chat.setGroupName(group.getGroupName());
			chat.setMembers(memberList);
			chat.setNotMembers(notMemberList);
			chat.setMessages(messageList);

			chats.put(groupId, chat);
		}

		OracleConnectionManager.getInstance().closeConnection();

		Object[] chatData = new Object[4];
		chatData[0] = chatId;
		chatData[1] = chats;
		chatData[2] = userString;
		chatData[3] = allUserList;

		return chatData;
	}
}
