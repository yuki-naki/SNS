package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.User;

public class MyProfileDao{

	public User getMyProfile(User u){

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		User user = u;

//		try{
//			cn = OracleConnectionManager.getInstance().getConnection();
//
//
//		}catch(SQLException e){
//
//		}catch(IOException e){
//
//		}


		return user;
	}

}
