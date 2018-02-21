package command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

		Map<String, Chat> chats = new HashMap<String, Chat>();

		// 自分が所属しているグループのIDリスト取得
		ArrayList<String> belongGroupIdList = (ArrayList<String>)groupMemberDao.getBelongGroupIdList(loginUserId);

		// フォローしている人を取得
		List<String> followIdList = (ArrayList<String>)followDao.getFollowIdList(loginUserId);
		List<User> followList = userDao.getUserList(followIdList);

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
			notMemberIdList.retainAll(followIdList);
			notMemberList = userDao.getUserList(notMemberIdList);

			// 所属グループのmessage
			List<Message> messageList = (ArrayList<Message>)messageDao.getMessageList(groupId);
			messageList.forEach(m -> m.setUser(userDao.getUserByUserId(m.getUserId())));
			messageList.forEach(m -> System.out.println(m.getContent()));

			chat.setGroupId(group.getGroupId());
			chat.setGroupName(group.getGroupName());
			chat.setGroupIcon(group.getGroupIcon());
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
		chatData[3] = followList;

		return chatData;
	}
}
