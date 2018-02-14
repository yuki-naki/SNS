package dao;

import bean.User;

public interface UserDao {
	User getUser(String login, String password);
	User getUserByUserId(String userId);
}
