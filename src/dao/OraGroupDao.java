package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

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

			String sql = "SELECT group_id, group_name, group_icon FROM group_t WHERE group_id = ?";
			st = cn.prepareStatement(sql);
			st.setString(1, groupId);

			rs = st.executeQuery();
			rs.next();
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


	public ArrayList<Group> getGroupList(ArrayList<String> groupIdList){
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<Group> groupList = new ArrayList<Group>();
		Iterator<String> it = groupIdList.iterator();

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();

			while(it.hasNext()){
				String sql = "SELECT * FROM group_t WHERE group_id = ?";
			st = cn.prepareStatement(sql);
				st.setString(1, it.next());
			rs = st.executeQuery();
				while(rs.next()){
					Group group = new Group();
					group.setGroupId(rs.getString(1));
					group.setGroupName(rs.getString(2));
					group.setGroupIcon(rs.getBlob(3));
					groupList.add(group);
				}
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
		return groupList;
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
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();

			rs.next();
			groupId = rs.getString(1);

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

	public void deleteGroup(String deleteTargetGroupId){
		PreparedStatement st = null;

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "DELETE FROM chat_t WHERE chat_group_id = ?";
			st = cn.prepareStatement(sql);
			st.setString(1, deleteTargetGroupId);
			st.executeUpdate();

			sql = "DELETE FROM groupmember_t WHERE group_id = ?";
			st.close();
			st = cn.prepareStatement(sql);
			st.setString(1, deleteTargetGroupId);
			st.executeUpdate();

			sql = "DELETE FROM group_t WHERE group_id = ?";
			st.close();
			st = cn.prepareStatement(sql);
			st.setString(1, deleteTargetGroupId);
			st.executeUpdate();

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

	public void groupUpdate(InputStream input,long inputsize,Group group){

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;

		System.out.println("groupName:"+group.getGroupName());


		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "update group_t set group_icon = ?,group_name = ? where group_id = ?";
			st = cn.prepareStatement(sql);
			st.setBinaryStream(1,input,(int)inputsize);
			st.setString(2, group.getGroupName());
			st.setString(3,group.getGroupId());

			st.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(st != null){
					st.close();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}

	}

	public void groupUpdate(Group group){

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;



		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "update group_t set group_name = ? where group_id = ?";
			st = cn.prepareStatement(sql);

			st.setString(1, group.getGroupName());
			st.setString(2,group.getGroupId());

			st.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(st != null){
					st.close();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}

	}
}
