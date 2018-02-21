package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OraGroupMemberDao implements GroupMemberDao{
	public List<String> getGroupMemberIdList(String groupId) {
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<String> groupMemberIdList = new ArrayList<String>();

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();
			String sql = "SELECT user_Id FROM groupmember_t WHERE group_id = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, groupId);

			rs = st.executeQuery();

			while(rs.next())
			{
				groupMemberIdList.add(rs.getString(1));
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
		return groupMemberIdList;
	}

	public List<String> getNotGroupMemberIdList(String groupId) {
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<String> notGroupMemberIdList = new ArrayList<String>();

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();
			String sql = "SELECT user_id FROM user_t WHERE user_id NOT IN "
						+"(SELECT user_id FROM groupmember_t WHERE group_id = ?)";

			st = cn.prepareStatement(sql);
			st.setString(1, groupId);
			rs = st.executeQuery();

			while(rs.next())
			{
				notGroupMemberIdList.add(rs.getString(1));
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
		return notGroupMemberIdList;
	}

	public List<String> getBelongGroupIdList(String userId){
		PreparedStatement st = null;
		ResultSet rs = null;

		List<String> groupIdList = new ArrayList<String>();

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "SELECT group_id FROM groupmember_t WHERE user_id = ?";
			st = cn.prepareStatement(sql);
			st.setString(1, userId);

			rs = st.executeQuery();
			while(rs.next()){
				groupIdList.add(rs.getString(1));
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
		return groupIdList;
	}

	public void addGroupMember(String groupId, ArrayList<String> members){
		PreparedStatement st = null;

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

	public void removeGroupMember(String groupId, ArrayList<String> members){
		PreparedStatement st = null;
		ResultSet rs = null;

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();

			for(int i = 0; i < members.size(); i++){
				String sql = "DELETE FROM groupmember_t WHERE group_id = ? AND user_id = ?";
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
