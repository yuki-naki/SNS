package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import bean.MyProfile;

public class MyProfileDao{

	public void addMyProfile(String Id,String user_introduction){

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		ArrayList list = new ArrayList();
		MyProfile bean = new MyProfile();

		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "Update user_t Set user_introduction = ? where user_id = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, user_introduction);
			st.setString(2, Id);

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
		MyProfile bean = new MyProfile();

		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = " select t1.username,TO_CHAR(sysdate,'YYYY') - t1.admission_year  + case when (TO_CHAR(sysdate,'MMDD')<TO_CHAR(0331)) AND (TO_CHAR(sysdate,'MMDD')<TO_CHAR(0101)) then 0 else 1 end,t3.department_name ,t1.user_introduction,t1.user_icon from user_t t1,Belong_department_t t2,department_t t3 where t1.user_id = t2.user_id and t2.department_id = t3.department_id and t1.user_id = "+userId;

			st = cn.prepareStatement(sql);

			rs = st.executeQuery();

			rs.next();
			bean.setUserName(rs.getString(1));
			bean.setSchoolYear(rs.getString(2));
			bean.setDepartmentName(rs.getString(3));
			bean.setUserIntroduction(rs.getString(4));
			byte[] imgData = rs.getBytes(5);
 //           request.setAttribute("rvi", "Ravinath");
 //           rs.getString("teatitle");

            bean.setIcon(Base64.getEncoder().encodeToString(imgData));

			System.out.println(bean.getUserName());

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

			return list;
		}

}
