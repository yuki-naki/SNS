package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowDao {
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
		//ers;return followUs
	}
}
