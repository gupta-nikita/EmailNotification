package com.ebay.email.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.spy.memcached.MemcachedClient;

import org.apache.log4j.helpers.ISO8601DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.ebay.email.dao.NotificationDao;
import com.ebay.email.model.Notifications;
import com.google.gson.Gson;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	private MemcachedClient memcachedClient;
	
	private NotificationDao notificationDao;
	private List<Notifications> notifications;
	private Gson gson;
	
	private final static int WAITTIME = 3600;
	private String CACHEKEY;
	
	/*
	 * Get All Notification  
	 */
	
	@Override
	@SuppressWarnings("unchecked")
	public String getAllNotifications(){
		gson = new Gson();
		CACHEKEY = "AllNotifications";
		
		notifications = (List<Notifications>) memcachedClient.get(CACHEKEY);
		if(notifications!=null){
			System.out.println("Reading from CAche");
			return gson.toJson(notifications);
		}
		notifications = notificationDao.getAllNotifications();
		if(notifications!=null){
			memcachedClient.set(CACHEKEY, WAITTIME, notifications);
			System.out.println("Writing to Cache");
		}
		return gson.toJson(notifications);
	}
	
	/*
	 *  Insert Notification
	 */
	
	@Override
	public int insertNotification(Notifications notification){
		notification.setDate(new Date());
		return notificationDao.insertNotification(notification);
	}
	
	/*
	 * Get Notification By UserId
	 */
	
	@Override
	@SuppressWarnings("unchecked")
	public String getNotificationByUserId(int user_id){
		gson = new Gson();
		CACHEKEY = "User"+user_id;
		
		notifications = (List<Notifications>) memcachedClient.get(CACHEKEY);
		if(notifications!=null){
			return gson.toJson(notifications);
		}
		notifications = notificationDao.getNotificationByUserId(user_id);
		if(notifications!=null){
			memcachedClient.set(CACHEKEY, WAITTIME, notifications);
		}
		return gson.toJson(notifications);
	}
	
	/*
	 * Get Notification By Date
	 */
	@Override
	@SuppressWarnings("unchecked")
	public String getNotificationByDate(Date date) {
		gson = new Gson();
		
		// Changing Date Format To ISO if not already
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z' ");
		String dateISO = df.format(date);
		try {
			date = df.parse(dateISO);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		CACHEKEY = "Date" + date;
		
		notifications = (List<Notifications>) memcachedClient.get(CACHEKEY);
		if(notifications != null){
			return gson.toJson(notifications);
		}
		
		notifications = notificationDao.getNotificationByDate(date);
		if(notifications!= null){
			memcachedClient.set(CACHEKEY,WAITTIME, notifications);
		}
		return gson.toJson(notifications);
	}
	
	/*
	 * Get Notification By Day Interval
	 */
	
	@Override
	@SuppressWarnings("unchecked")
	public String getNotificationByDayInterval(int days){
		gson = new Gson();
		long base = System.currentTimeMillis();
		Date currentDate = new Date(base);
		Date date = new Date(base - (days*24*60*60*1000));
		
		//Extracting only date from datetime
		DateFormat dateformat = new SimpleDateFormat("MM-dd-yyyy");
		String key = dateformat.format(currentDate);
		
		CACHEKEY = key+"date_interval"+days;
		
		notifications = (List<Notifications>) memcachedClient.get(CACHEKEY);
		if(notifications!= null){
			return gson.toJson(notifications);
		}
		notifications = notificationDao.getNotificationByDayInterval(date);
		if(notifications!= null){
			memcachedClient.set(CACHEKEY, WAITTIME, notifications);
		}
		return gson.toJson(notifications);
	}
	
	/*
	 * Get Notification By Hour Interval
	 */
	
	@Override
	@SuppressWarnings("unchecked")
	public String getNotificationByHourInterval(int hours){
		gson = new Gson();
		long base = System.currentTimeMillis();
		Date date = new Date(base - (hours*60*60*1000));
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:00'Z' ");
		String dateISO = df.format(date);
		
		CACHEKEY = dateISO+"hour_interval"+hours;
		
		notifications = (List<Notifications>) memcachedClient.get(CACHEKEY);
		if(notifications!= null){
			return gson.toJson(notifications);
		}

		notifications = notificationDao.getNotificationByHourInterval(date);
		if(notifications!= null){
			memcachedClient.set(CACHEKEY, WAITTIME, notifications);
		}
		return gson.toJson(notifications);
	}
	
	@Autowired
	@Qualifier("notificationDao")
	public void setNotificationDao(NotificationDao notificationDao){
		this.notificationDao = notificationDao;
	}

}
