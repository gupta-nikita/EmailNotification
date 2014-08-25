package com.ebay.email.dao;

import java.util.Date;
import java.util.List;

import com.ebay.email.model.Notifications;

public interface NotificationDao {

	List<Notifications> getAllNotifications();

	int insertNotification(Notifications notification);

	List<Notifications> getNotificationByDate(Date date);

	List<Notifications> getNotificationByDayInterval(Date date);

	List<Notifications> getNotificationByHourInterval(Date date);

	List<Notifications> getNotificationByUserId(int user_id);

}