package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Group;

public class OraGroupDao implements GroupDao{
	public Group getGroup(String groupId){
		PreparedStatement st = null;
		ResultSet rs = null;

		Group group = new Group();

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "SELECT group_id, group_name, group_icon FROM group_t WHERE group_id = " + groupId + "'";

			st = cn.prepareStatement(sql);

			rs = st.executeQuery();

			group.setGroupId(rs.getString(1));
			group.setGroupName(rs.getString(2));
			group.setGroupIcon(rs.getBlob(3));

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
		return group;
	}

	public String createGroup(Group group){
		PreparedStatement st = null;
		ResultSet rs = null;
		String groupId = null;

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "INSERT INTO group_t(group_id, group_name, group_icon) VALUES(group_seq.nextval, ?, ?)";
			st = cn.prepareStatement(sql);
			st.setString(1, group.getGroupName());
			st.setBlob(2, group.getGroupIcon());
			st.executeUpdate();

			sql = "SELECT group_seq.currval FROM dual";
			st.close();
			Statement statement = cn.createStatement();
			rs = statement.executeQuery(sql);
			groupId = rs.getString(1);
			System.out.println("groupdao81" + groupId);

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

		return groupId;
	}

	public ArrayList<String> getBelongGroups(String userId){
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<String> groups = new ArrayList<String>();

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "SELECT group_id FROM groupmember_t WHERE user_id = ?";
			st = cn.prepareStatement(sql);
			st.setString(1, userId);

			rs = st.executeQuery();
			while(rs.next()){
				groups.add(rs.getString(1));
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
		return groups;
	}
}
