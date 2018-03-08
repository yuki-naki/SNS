package command;

import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.NotificationDao;
import dao.OracleConnectionManager;

public class DeleteNotifCommand extends AbstractCommand {

	public ResponseContext execute(ResponseContext resc) {

		RequestContext requestContext = getRequestContext();
		String[] deleteNotifIdTab = requestContext.getParameter("notificationId");

		if(deleteNotifIdTab != null){
			String deleteNotifId = deleteNotifIdTab[0];

			AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

			OracleConnectionManager.getInstance().beginTransaction();

			NotificationDao notificationDao = factory.getNotificationDao();
			notificationDao.deleteNotification(deleteNotifId);

			resc.setResult(notificationDao.getAllNotifications());

			OracleConnectionManager.getInstance().closeConnection();
		}

		resc.setTarget("topPage");

		return resc;
	}
}
