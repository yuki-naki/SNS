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

			String sql = "SELECT user_id,login_id, password, is_admin, username, user_introduction, student_id, admission_year, d.department_name "
					+ "FROM user_t u, department_t d WHERE login_id = ? AND password = ? AND u.department_id=d.department_id";
			st = cn.prepareStatement(sql);
			st.setString(1, loginId);
			st.setString(2, password);

			rs = st.executeQuery();

			if(rs.next()){
				user = new User();
				String userId = rs.getString(1);
				int isAdmin = rs.getInt(4);
				String username = rs.getString(5);
				String userIntroduction = rs.getString(6);
				String studentId = rs.getString(7);
				String admissionYear = rs.getString(8);
				String departmentName = rs.getString(9);

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
				user.setUserIntroduction(userIntroduction);
				user.setStudentId(studentId);
				user.setAdmissionYear(admissionYear);
				user.setDepartmentName(departmentName);
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

	public User getUserByUserId(String userId){

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		User user = new User();

		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "SELECT user_id FROM user_t WHERE user_id = ?";
			st = cn.prepareStatement(sql);
			st.setString(1, userId);

			rs = st.executeQuery();

			user.setUserId(userId);
			user.setUsername(rs.getString(1));
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

	public Blob getIcon(String userId){
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		Blob blob = null;
		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "SELECT user_icon FROM user_t WHERE user_id = ?";
			st = cn.prepareStatement(sql);
			st.setString(1, userId);

			rs = st.executeQuery();

			rs.next();
			blob = rs.getBlob(1);
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
		return blob;
	}
}
