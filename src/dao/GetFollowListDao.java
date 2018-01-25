package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetFollowListDao {
	public List getFollowList(String userId){
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList followUsers = new ArrayList();

		try
		{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();
			String sql = "SELECT followed_user_id FROM follow_t WHERE follower_user_id = " + userId;

			st = cn.prepareStatement(sql);

			rs = st.executeQuery();

			while(rs.next())
			{
				User user = new User();

				user.setUserId(rs.getString(1));

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
}
