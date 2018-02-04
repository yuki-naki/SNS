package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Notification;

public class OraNotificationDao implements NotificationDao {

	public void addNotification(Notification notification) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = OracleConnectionManager.getInstance().getConnection();

			String sql = "INSERT INTO notification_t VALUES (notification_seq.nextVal, ?, SYSDATE)";

			ps = conn.prepareStatement(sql);

			ps.setString(1, notification.getNotificationContent());

			ps.executeUpdate();

			conn.commit();
		}
		catch(SQLException e){
			OracleConnectionManager.getInstance().rollback();
		}
		finally {
			try {
				if(ps != null){
					ps.close();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

	public List getAllNotifications() {

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		List<Notification> notifications = new ArrayList<Notification>();

		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			String sql = "SELECT notification_id, notification_content, TO_CHAR(notification_date,'yyyy/mm/dd HH24:MI') "
					+ "FROM notification_t ORDER BY notification_date DESC";
			st = cn.prepareStatement(sql);

			rs = st.executeQuery();

			while(rs.next()){
				Notification notification = new Notification();

				String notificationId = rs.getString(1);
				String notificationContent = rs.getString(2);
				String notificationDate = rs.getString(3);

				notification.setNotificationId(notificationId);
				notification.setNotificationContent(notificationContent);
				notification.setNotificationDate(notificationDate);

				notifications.add(notification);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(st != null){
					st.close();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return notifications;
	}
}
