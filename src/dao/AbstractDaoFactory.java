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
			prop.load(new FileInputStream("../workspace/FrontController/src/dao.properties"));
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

	public abstract RemoveFollowDao getRemoveFollowDao();
	public abstract LoginDao getLoginDao();
	public abstract FollowDao getFollowDao();
}
