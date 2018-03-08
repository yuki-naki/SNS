package dao;

import java.util.List;

import bean.Notification;

public interface NotificationDao {

	public void addNotification(Notification notification);
	public void deleteNotification(String notificationId);
	public List getAllNotifications();
}
