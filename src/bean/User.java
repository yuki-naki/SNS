package bean;

import java.io.Serializable;

public class User implements Serializable {

	private String userId;
	private String loginId;
	private String password;
	private boolean isAdmin;
	private String username;
	private String userIntroduction;
	private String studentId;
	private String admissionYear;
	//新しい追加 2018.2.15
	private String departmentName;

	//新しい追加 2018.2.15
	public String getDepartmentName(){
		return departmentName;
	}
	//新しい追加 2018.2.15
	public void  setDepartmentName(String departmentName){
		this.departmentName = departmentName;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserIntroduction() {
		return userIntroduction;
	}
	public void setUserIntroduction(String userIntroduction) {
		this.userIntroduction = userIntroduction;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getAdmissionYear() {
		return admissionYear;
	}
	public void setAdmissionYear(String admissionYear){
		this.admissionYear = admissionYear;
	}
}
