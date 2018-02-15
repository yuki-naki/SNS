package bean;

import java.io.Serializable;

public class MyProfile implements Serializable {

	private String userName;
	private String schoolYear;
	private String departmentName;
	private String userIntroduction;
	private String icon;
	private String userId;



	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return this.userName;
	}

	public void setSchoolYear(String schoolYear){
		this.schoolYear = schoolYear;
	}
	public String getSchoolYear(){
		return this.schoolYear;
	}

	public void setDepartmentName(String departmentName){
		this.departmentName = departmentName;
	}
	public String getDepartmentName(){
		return this.departmentName;
	}

	public void setUserIntroduction(String userIntroduction){
		this.userIntroduction = userIntroduction;
	}
	public String getUserIntroduction(){
		return this.userIntroduction;
	}
	public void setIcon(String str){
		this.icon = str;
	}
	public String getIcon(){
		return this.icon;
	}
}
