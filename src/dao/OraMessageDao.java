package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Message;

public class OraMessageDao implements MessageDao {

	public void addMessage(Message message){

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = OracleConnectionManager.getInstance().getConnection();

			String sql = "INSERT INTO chat_t VALUES (?, ?, ?, SYSDATE)";

			ps = conn.prepareStatement(sql);

			ps.setString(1, message.getUser().getUserId());
			ps.setString(2, message.getGroupId());
			ps.setString(3, message.getContent());

			ps.executeUpdate();

			conn.commit();
		}
		catch(SQLException e){
			OracleConnectionManager.getInstance().rollback();
		}
		finally {
			try {
				if(ps != null){
					ps.close();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

	public List<Message> getMessageList(String groupId){
		PreparedStatement st = null;
		ResultSet rs = null;

		List<Message> messageList = new ArrayList<Message>();

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "SELECT chat_user_id, chat_group_id, chat_content, TO_CHAR(chat_date,'yyyy/mm/dd HH24:MI') FROM chat_t WHERE chat_group_id = ? ORDER BY chat_date";
			st = cn.prepareStatement(sql);
			st.setString(1, groupId);
			rs = st.executeQuery();

			while(rs.next()){
				Message message = new Message();
				message.setUserId(rs.getString(1));
				message.setGroupId(rs.getString(2));
				message.setContent(rs.getString(3));
				message.setDate(rs.getString(4));

				messageList.add(message);
			}

			cn.commit();
		}
		catch(SQLException e)
		{
			OracleConnectionManager.getInstance().rollback();
		}
		finally
		{
			try
			{
				if(rs != null)
				{
					rs.close();
				}
				if(st != null)
				{
					st.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return messageList;
	}
}
