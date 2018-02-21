package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OraFollowDao implements FollowDao{
	public void follow(String followerUserId, String followedUserId){
		PreparedStatement st = null;
		ResultSet rs = null;

		try{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO follow_t(follower_user_id, followed_user_id) VALUES(" + followerUserId + ", " + followedUserId + ")";

			st = cn.prepareStatement(sql);

			st.executeUpdate();

			cn.commit();
		}
		catch(SQLException e){
			OracleConnectionManager.getInstance().rollback();
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

	public void removeFollow(String followerUserId, String removeTargetUserId){
		PreparedStatement st = null;
		ResultSet rs = null;

		try{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM follow_t WHERE follower_user_id = '" + followerUserId + "' AND followed_user_id = '" + removeTargetUserId + "'";

			st = cn.prepareStatement(sql);

			st.executeUpdate();

			cn.commit();
		}
		catch(SQLException e){
			OracleConnectionManager.getInstance().rollback();
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

	public List<String> getFollowIdList(String followerUserId){
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<String> followUserIdList = new ArrayList<String>();

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "SELECT followed_user_id FROM follow_t WHERE follower_user_id = ?";

			st = cn.prepareStatement(sql);
			st.setString(1, followerUserId);

			rs = st.executeQuery();

			while(rs.next())
			{
				followUserIdList.add(rs.getString(1));
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
		return followUserIdList;
	}

	public List<String> getUnFollowIdList(String followerUserId){
		PreparedStatement st = null;
		ResultSet rs = null;

		List<String> unFollowUserIdList = new ArrayList<String>();

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();
			String sql = "SELECT user_id FROM user_t WHERE user_id NOT IN "
						+"(SELECT followed_user_id FROM follow_t WHERE follower_user_id = ?) AND user_id != ?";

			st = cn.prepareStatement(sql);
			st.setString(1, followerUserId);
			st.setString(2, followerUserId);

			rs = st.executeQuery();

			while(rs.next())
			{
				unFollowUserIdList.add(rs.getString(1));
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
		return unFollowUserIdList;
	}
}
