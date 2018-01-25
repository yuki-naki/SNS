package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	public String login(String loginId, String password){
		PreparedStatement st = null;
		ResultSet rs = null;
		String userId = null;

		try{
			Connection cn = null;
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "SELECT user_id FROM user_t WHERE user_login_id = " + loginId + "AND user_password = " + password;

			st = cn.prepareStatement(sql);

			rs = st.executeQuery();

			userId = rs.getString(1);

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

		return userId;
	}
}
