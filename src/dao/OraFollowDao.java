package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.User;

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

	public List getFollowList(String followerUserId){
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList followUsers = new ArrayList();

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "SELECT user_id, username FROM user_t WHERE user_id IN "
					+"(SELECT followed_user_id FROM follow_t WHERE follower_user_id = '" + followerUserId + "')";

			st = cn.prepareStatement(sql);

			rs = st.executeQuery();

			while(rs.next())
			{
				User user = new User();

				user.setUserId(rs.getString(1));
				user.setUsername(rs.getString(2));

				followUsers.add(user);
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
		return followUsers;
	}

	public List getUnFollowList(String followerUserId){
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList unFollowUsers = new ArrayList();

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();
			String sql = "SELECT user_id, username FROM user_t WHERE user_id NOT IN "
						+"(SELECT followed_user_id FROM follow_t WHERE follower_user_id = '" + followerUserId + "') AND user_id != '" + followerUserId + "'";

			st = cn.prepareStatement(sql);

			rs = st.executeQuery();

			while(rs.next())
			{
				User user = new User();

				user.setUserId(rs.getString(1));
				user.setUsername(rs.getString(2));

				unFollowUsers.add(user);
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
		return unFollowUsers;
	}
}
