package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;

public class OraUserDao implements UserDao {

	public User getUser(String loginId, String password){

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		User user = null;

		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM user_t WHERE login_id = ? AND password = ?";
			st = cn.prepareStatement(sql);
			st.setString(1, loginId);
			st.setString(2, password);

			rs = st.executeQuery();

			if(rs.next()){
				user = new User();
				String userId = rs.getString(1);
				int isAdmin = rs.getInt(4);
				String username = rs.getString(5);
				Blob icon = rs.getBlob(6);
				String userIntroduction = rs.getString(7);
				String studentId = rs.getString(8);
				String admissionYear = rs.getString(9);
				String grouping = rs.getString(10);

				user.setUserId(userId);
				user.setLoginId(loginId);
				user.setPassword(password);

				if(isAdmin == 1){
					user.setAdmin(true);
				}
				else {
					user.setAdmin(false);
				}

				user.setUsername(username);
				user.setIcon(icon);
				user.setUserIntroduction(userIntroduction);
				user.setStudentId(studentId);
				user.setAdmissionYear(admissionYear);
				user.setGrouping(grouping);
			}
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
