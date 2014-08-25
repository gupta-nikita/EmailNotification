package com.ebay.email.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ebay.email.model.Notifications;

@Repository("notificationDao")
public class NotificationDaoImpl implements NotificationDao{

	@Autowired
	MongoOperations mongoOperations;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	private Query query;
	
	/*
	 * Get All Notification
	 */
	@Override
	public List<Notifications> getAllNotifications(){
		return mongoOperations.findAll(Notifications.class);
	}
	
	/*
	 * Insert Notification
	 */
	
	@Override
	public int insertNotification(Notifications notification){
		if(!mongoOperations.collectionExists(Notifications.class)){
			mongoOperations.createCollection(Notifications.class);
		}
		mongoOperations.insert(notification);
		return (notification.getNotification_id());
		
	}
	
	/*
	 * Get Notification By User Id
	 */
	
	@Override
	public List<Notifications> getNotificationByUserId(int user_id){
		query = new Query(Criteria.where("user_id").is(user_id));
		
		List<Notifications> notifications = mongoOperations.find(query,Notifications.class);
		return notifications;
	}
	
	/*
	 * Get Notification By Date
	 */
	
	@Override 
	public List<Notifications> getNotificationByDate(Date date){
		query = new Query(Criteria.where("date").is(date));
		
		List<Notifications> notifications = mongoOperations.find(query, Notifications.class);
		return notifications;
	}
	
	/*
	 * Get Notification By Date Interval
	 */
	@Override 
	public List<Notifications> getNotificationByDayInterval(Date date){
		query = new Query(Criteria.where("date").gte(date));
		
		List<Notifications> notifications = mongoOperations.find(query, Notifications.class);
		return notifications;
	}
	
	/*
	 * Get Notification By Hour Interval
	 */
	@Override
	public List<Notifications> getNotificationByHourInterval(Date date){
		query = new Query(Criteria.where("date").gte(date));
		
		List<Notifications> notifications = mongoOperations.find(query, Notifications.class);
		return notifications;
	}
	
	
}
