package dao;

public class OraDaoFactory extends AbstractDaoFactory
{
	public RemoveFollowDao getRemoveFollowDao()
	{
		return new RemoveFollowDao();
	}
}
