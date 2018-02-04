package dao;

import java.util.List;

import bean.Notification;

public interface NotificationDao {

	public void addNotification(Notification notification);
	public List getAllNotifications();
}
