package com.ebay.email.service;

import java.util.Date;

import com.ebay.email.model.Notifications;

public interface NotificationService {
	String getAllNotifications();
	int insertNotification(Notifications notification);
	String getNotificationByUserId(int user_id);
	String getNotificationByDate(Date date);
	String getNotificationByDayInterval(int days);
	String getNotificationByHourInterval(int hours);

}
