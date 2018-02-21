package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

	public  List<User> getUserList(List<String> userIdList){
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;

		List<User> userList = new ArrayList<User>();

		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			Iterator<String> userIdListIterator = userIdList.iterator();

			while(userIdListIterator.hasNext()){
				String userId = userIdListIterator.next();

				String sql = "SELECT user_id, is_admin, username, user_introduction, student_id, admission_year, d.department_name "
						+ "FROM user_t u, department_t d WHERE user_id = ? AND u.department_id=d.department_id";
				st = cn.prepareStatement(sql);
				st.setString(1, userId);

				rs = st.executeQuery();

				while(rs.next()){
					User user = new User();
					user.setUserId(rs.getString(1));
					int isAdmin = rs.getInt(2);
					if(isAdmin == 1){
						user.setAdmin(true);
					}
					else {
						user.setAdmin(false);
					}
					user.setUsername(rs.getString(5));
					user.setUserIntroduction(rs.getString(4));
					user.setStudentId(rs.getString(5));
					user.setAdmissionYear(rs.getString(6));
					user.setDepartmentName(rs.getString(7));

					userList.add(user);
				}
			}
			return userList;
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
		return userList;
	}

	public User getUserByUserId(String userId){

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		User user = new User();

		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM user_t WHERE user_id = ?";
			st = cn.prepareStatement(sql);
			st.setString(1, userId);

			rs = st.executeQuery();

			rs.next();
			user.setUserId(rs.getString(1));
			user.setLoginId(rs.getString(2));
			user.setPassword(rs.getString(3));
			int isAdmin = rs.getInt(4);
			if(isAdmin == 1){
				user.setAdmin(true);
			}
			else {
				user.setAdmin(false);
			}
			user.setUsername(rs.getString(5));
			user.setUserIntroduction(rs.getString(7));
			user.setStudentId(rs.getString(8));
			user.setAdmissionYear(rs.getString(9));
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
