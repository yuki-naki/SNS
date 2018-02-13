package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;

public class MyProfileDao{

	public User getMyProfile(User u){

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		User user = u;

		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "Select ";
			st = cn.prepareStatement(sql);


			rs = st.executeQuery();

		}
		catch(SQLException e){
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

			return user;
		}

}
