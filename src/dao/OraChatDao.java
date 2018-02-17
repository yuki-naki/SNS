package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Chat;
import bean.Message;
import bean.User;

public class OraChatDao implements ChatDao {

	public Map getAllChats(String sessionUserId) {

		PreparedStatement st = null;
		ResultSet rsGroup = null;
		ResultSet rsUser = null;
		ResultSet rsMessage = null;
		ResultSet rsMsgUser = null;
		Connection cn = null;
		Map<String, Chat> chats = new HashMap<String,Chat>();

		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "SELECT g.group_id, g.group_name, g.group_icon FROM group_t g, groupmember_t gm "
					+ "WHERE g.group_id = gm.group_id AND gm.user_id = ?";
			st = cn.prepareStatement(sql);
			st.setString(1, sessionUserId);

			rsGroup = st.executeQuery();

			while(rsGroup.next()){
				Chat chat = new Chat();

				String groupId = rsGroup.getString(1);
				String groupName = rsGroup.getString(2);
				Blob groupIcon = rsGroup.getBlob(3);

				chat.setGroupId(groupId);
				chat.setGroupName(groupName);
				chat.setGroupIcon(groupIcon);

				sql = "SELECT gm.user_id, u.username FROM group_t g, groupmember_t gm, user_t u"
						+ " WHERE g.group_id = gm.group_id AND gm.user_id = u.user_id AND g.group_id = ?";
				st = cn.prepareStatement(sql);
				st.setString(1, groupId);

				rsUser = st.executeQuery();

				List<User> users = new ArrayList<User>();
				while(rsUser.next()){
					User user = new User();
					String userId = rsUser.getString(1);
					String username = rsUser.getString(2);

					user.setUserId(userId);
					user.setUsername(username);

					users.add(user);
				}

				chat.setUsers(users);

				sql = "SELECT c.chat_user_id, c.chat_group_id, c.chat_content, TO_CHAR(c.chat_date,'yyyy/mm/dd HH24:MI') FROM"
						+ " groupmember_t gm, chat_t c WHERE gm.group_id = c.chat_group_id AND gm.user_id = c.chat_user_id AND c.chat_group_id = ?"
						+ " ORDER BY c.chat_date";

				st = cn.prepareStatement(sql);
				st.setString(1, groupId);

				rsMessage = st.executeQuery();

				List<Message> messages = new ArrayList<Message>();
				while(rsMessage.next()){
					Message message = new Message();

					String chatUserId = rsMessage.getString(1);
					String chatGroupId = rsMessage.getString(2);
					String chatContent = rsMessage.getString(3);
					String chatDate = rsMessage.getString(4);

					sql = "SELECT username FROM user_t WHERE user_id = ?";
					st = cn.prepareStatement(sql);
					st.setString(1, chatUserId);

					rsMsgUser = st.executeQuery();

					if(rsMsgUser.next()){
						User user = new User();

						String username = rsMsgUser.getString(1);

						user.setUserId(chatUserId);
						user.setUsername(username);

						message.setUser(user);
					}

					message.setGroupId(chatGroupId);
					message.setContent(chatContent);
					message.setDate(chatDate);

					messages.add(message);
				}

				chat.setMessages(messages);

				chats.put(groupId,chat);
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
				if(rsMsgUser != null){
					rsGroup.close();
				}
				if(st != null){
					st.close();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return chats;
	}
}