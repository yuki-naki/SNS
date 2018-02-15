package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OraGroupMemberDao implements GroupMemberDao{
	public List<String> getGroupMember(String groupId) {
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<String> groupMembers = new ArrayList<String>();

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();
			String sql = "SELECT user_id FROM groupmember_t WHERE group_id = '" + groupId + "'";

			st = cn.prepareStatement(sql);

			rs = st.executeQuery();

			while(rs.next())
			{
				groupMembers.add(rs.getString(1));
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
		return groupMembers;
	}

	public void addGroupMember(String groupId, ArrayList<String> members){
		PreparedStatement st = null;
		ResultSet rs = null;

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();

			for(int i = 0; i < members.size(); i++){
				String sql = "INSERT INTO groupmember_t(group_id, user_id) VALUES(?, ?)";
				st = cn.prepareStatement(sql);

				st.setString(1, groupId);
				st.setString(2, (String)members.get(i));

				st.executeUpdate();
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
	}
}
