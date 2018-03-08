package dao;

import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import bean.User;

public interface UserDao {
	User getUser(String login, String password);
	List<User> getAllUserList(String userId);
	List<User> getUserList(List<String> userIdList);
	User getUserByUserId(String userId);
	public Blob getIcon(String userId);
	public void iconUpdate(InputStream input,long inputsize,String id);
	public void addMyProfile(User u);
}
