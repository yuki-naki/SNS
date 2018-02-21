package dao;

import java.util.List;

import bean.User;

public interface UserDao {
	User getUser(String login, String password);
	List<User> getUserList(List<String> userIdList);
	User getUserByUserId(String userId);
}
