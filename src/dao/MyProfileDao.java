package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.MyProfile;

public class MyProfileDao{

	public List getMyProfile(String userId){

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		ArrayList list = new ArrayList();
		MyProfile bean = new MyProfile();

		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = " select t1.userName,TO_CHAR(sysdate,'YYYY') - t1.admission_year  + case when (TO_CHAR(sysdate,'MMDD')<TO_CHAR(0331)) AND (TO_CHAR(sysdate,'MMDD')<TO_CHAR(0101)) then 0 else 1 end,t3.department_name ,t1.user_introduction from user_t t1,Belong_department_t t2,department_t t3 where  t1.user_id ="+userId;

			st = cn.prepareStatement(sql);

			System.out.println("SQL実行");
			rs = st.executeQuery();

			rs.next();
			bean.setUserName(rs.getString(1));
			bean.setSchoolYear(rs.getString(2));
			bean.setDepartmentName(rs.getString(3));
			bean.setUserIntroduction(rs.getString(4));

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
