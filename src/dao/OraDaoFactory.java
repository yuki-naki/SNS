package dao;

public class OraDaoFactory extends AbstractDaoFactory {

	public UserDao getUserDao() {
		return new OraUserDao();
	}

	public FollowDao getFollowDao() {
		return new OraFollowDao();
	}


	public NotificationDao getNotificationDao() {
		return new OraNotificationDao();
	}

	public ChatDao getChatDao() {
		return new OraChatDao();
	}

	public MessageDao getMessageDao() {
		return new OraMessageDao();
	}
}
