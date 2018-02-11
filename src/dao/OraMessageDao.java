package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
