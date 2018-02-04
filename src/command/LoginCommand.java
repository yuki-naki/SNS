package command;

import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.NotificationDao;

public class LoginCommand extends AbstractCommand{

	public ResponseContext execute(ResponseContext responseContext){

		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		NotificationDao notificationDao = factory.getNotificationDao();

		responseContext.setResult(notificationDao.getAllNotifications());
		responseContext.setTarget("topPage");

		return responseContext;
	}
}
