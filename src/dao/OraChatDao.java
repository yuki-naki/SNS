package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Chat;
import bean.Message;
import bean.User;

public class OraChatDao implements ChatDao {

	public List getAllChats() {

		PreparedStatement stGroup = null;
		PreparedStatement stUser = null;
		PreparedStatement stMessage = null;
		ResultSet rsGroup = null;
		ResultSet rsUser = null;
		ResultSet rsMessage = null;
		Connection cn = null;
		List<Chat> chats = new ArrayList<Chat>();

		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM group_t";
			stGroup = cn.prepareStatement(sql);

			rsGroup = stGroup.executeQuery();

			while(rsGroup.next()){
				Chat chat = new Chat();

				String groupId = rsGroup.getString(1);
				String groupName = rsGroup.getString(2);
				Blob groupIcon = rsGroup.getBlob(3);

				chat.setGroupId(groupId);
				chat.setGroupName(groupName);
				chat.setGroupIcon(groupIcon);

				sql = "SELECT gm.user_id, u.username, u.user_icon FROM group_t g, groupmember_t gm, user_t u"
						+ " WHERE g.group_id = gm.group_id AND gm.user_id = u.user_id AND g.group_id = ?";
				stUser = cn.prepareStatement(sql);
				stUser.setString(1, groupId);

				rsUser = stUser.executeQuery();

				List<User> users = new ArrayList<User>();
				while(rsUser.next()){
					User user = new User();
					String userId = rsUser.getString(1);
					String username = rsUser.getString(2);
					Blob icon = rsUser.getBlob(3);

					user.setUserId(userId);
					user.setUsername(username);
					user.setIcon(icon);

					users.add(user);
				}

				chat.setUsers(users);

				sql = "SELECT c.chat_user_id, c.chat_group_id, c.chat_content, c.chat_date FROM"
						+ " groupmember_t gm, chat_t c WHERE gm.group_id = c.chat_group_id AND gm.user_id = c.chat_user_id AND c.chat_group_id = ?";

				stMessage = cn.prepareStatement(sql);
				stMessage.setString(1, groupId);

				rsMessage = stMessage.executeQuery();

				List<Message> messages = new ArrayList<Message>();
				while(rsMessage.next()){
					Message message = new Message();

					String chatUserId = rsMessage.getString(1);
					String chatGroupId = rsMessage.getString(2);
					String chatContent = rsMessage.getString(3);
					String chatDate = rsMessage.getString(4);

					message.setUserId(chatUserId);
					message.setGroupId(chatGroupId);
					message.setContent(chatContent);
					message.setDate(chatDate);

					messages.add(message);
				}

				chat.setMessages(messages);

				chats.add(chat);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rsGroup != null){
					rsGroup.close();
				}
				if(rsUser != null){
					rsGroup.close();
				}
				if(rsMessage != null){
					rsGroup.close();
				}
				if(stGroup != null){
					stGroup.close();
				}
				if(stUser != null){
					stGroup.close();
				}
				if(stMessage != null){
					stGroup.close();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return chats;
	}
}
