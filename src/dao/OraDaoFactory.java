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

	public MessageDao getMessageDao() {
		return new OraMessageDao();
	}

	public GroupDao getGroupDao(){
		return new OraGroupDao();
	}

	public GroupMemberDao getGroupMemberDao(){
		return new OraGroupMemberDao();
	}
}
