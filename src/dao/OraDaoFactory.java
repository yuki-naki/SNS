package dao;

public class OraDaoFactory extends AbstractDaoFactory {

	public RemoveFollowDao getRemoveFollowDao(){
		return new RemoveFollowDao();
	}

	public UserDao getUserDao() {
		return new OraUserDao();
	}

	public FollowDao getFollowDao() {
		return null;
	}

	public FollowsListDao getFollowsListDao() {
		return null;
	}
}
