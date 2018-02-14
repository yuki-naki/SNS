package dao;

import java.io.FileInputStream;
import java.util.Properties;

public abstract class AbstractDaoFactory
{
	public static AbstractDaoFactory getFactory()
	{
		AbstractDaoFactory factory = null;
		Properties prop = new Properties();

		try
		{
			prop.load(new FileInputStream("C:/workspace/SNS/src/dao.properties"));
			String name = prop.getProperty("dao");
			Class c = Class.forName(name);
			factory = (AbstractDaoFactory)c.newInstance();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return factory;
	}
	public abstract UserDao getUserDao();
	public abstract NotificationDao getNotificationDao();
	public abstract FollowDao getFollowDao();
	public abstract ChatDao getChatDao();
	public abstract MessageDao getMessageDao();
	public abstract GroupDao getGroupDao();
	public abstract GroupMemberDao getGroupMemberDao();
}
