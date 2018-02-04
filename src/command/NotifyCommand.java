package command;

import bean.Notification;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.NotificationDao;
import dao.OracleConnectionManager;

public class NotifyCommand extends AbstractCommand {

	public ResponseContext execute(ResponseContext responseContext) {

		RequestContext rc = getRequestContext();
		String textarea = rc.getTextarea();

		Notification notification = new Notification();
		notification.setNotificationContent(textarea);

		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

		OracleConnectionManager.getInstance().beginTransaction();

		NotificationDao notificationDao = factory.getNotificationDao();
		notificationDao.addNotification(notification);

		responseContext.setTarget("topPage");
		responseContext.setResult(notificationDao.getAllNotifications());

		OracleConnectionManager.getInstance().closeConnection();

		return responseContext;
	}
}