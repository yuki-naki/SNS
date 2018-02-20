package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.User;

public class MyProfileDao{

	public void addMyProfile(User u){

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		User user = u;

		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "Update user_t Set user_introduction = ? where user_id = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, user.getUserIntroduction());
			st.setString(2, user.getUserId());

			st.executeUpdate();
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
	}

	public void iconUpdate(InputStream input,long inputsize,String id){

		System.out.println("iconUpdateメソッドの実行");

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;

		System.out.println("id:"+id);
		System.out.println("inputsize:"+inputsize);


		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "update user_t set user_icon = ? where user_id = ?";
			st = cn.prepareStatement(sql);
			st.setBinaryStream(1,input,(int)inputsize);
			st.setString(2,id);

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

	public List getMyProfile(String userId){

		System.out.println("userId:"+userId);

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		ArrayList list = new ArrayList();
		User bean = new User();

		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = " select t1.user_Id, t1.username,TO_CHAR(sysdate,'YYYY') - t1.admission_year  + case when (TO_CHAR(sysdate,'MMDD')<TO_CHAR(0331)) AND (TO_CHAR(sysdate,'MMDD')<TO_CHAR(0101)) then 0 else 1 end,t2.department_name ,t1.user_introduction from user_t t1,department_t t2 where t1.department_id = t2.department_id and t1.user_id = "+userId;

			st = cn.prepareStatement(sql);

			rs = st.executeQuery();

			rs.next();
			bean.setUserId(rs.getString(1));
			bean.setUsername(rs.getString(2));
			bean.setAdmissionYear(rs.getString(3));
			bean.setDepartmentName(rs.getString(4));
			bean.setUserIntroduction(rs.getString(5));
			//byte[] imgData = rs.getBytes(5);
 //           request.setAttribute("rvi", "Ravinath");
 //           rs.getString("teatitle");

            //bean.setIcon(Base64.getEncoder().encodeToString(imgData));

			//System.out.println(bean.getUserName());

			//System.out.println("userId:"+bean.getUserId());

			list.add(bean);


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
			System.out.println("enddao");
			return list;
		}

}
