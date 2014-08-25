package com.ebay.email;

import java.util.Date;

import javax.management.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ebay.email.model.Notifications;
import com.ebay.email.service.NotificationService;
import com.google.gson.Gson;

@Controller
@SessionAttributes
public class NotificationController {
	
	private NotificationService notificationService;
	
	@Autowired
	MongoOperations mongoOperations;

	@RequestMapping(value="/getAllNotifications",method=RequestMethod.GET,produces="application/json")
	@ResponseBody()
	public String getAllNotifications(){
		return notificationService.getAllNotifications();
	}
	
//	@RequestMapping(value="/addNotifications")
//	public void addNotifications(){
//		if(!mongoOperations.collectionExists(Notifications.class)){
//			mongoOperations.createCollection(Notification.class);
//		}
//		
//		Date date = new Date();
//		mongoOperations.insert(new Notifications(2,1,"List",date));
//		mongoOperations.insert(new Notifications(3,2,"Bid",date));
//		mongoOperations.insert(new Notifications(2,5,"Buy",date));
//		mongoOperations.insert(new Notifications(9,10,"List",date));
//		
//	}
	
	/*
	 *  Insert notification
	 */
	
	@RequestMapping(value="/notification",method=RequestMethod.POST,consumes="application/json")
	public @ResponseBody void recordActivity(@RequestBody Notifications notification){
		int id = notificationService.insertNotification(notification);
		System.out.println(id + "Successfully inserted");
	}
	
	/*
	 * Find Notifications by UserId
	 */
	
	@RequestMapping(value="/notification/{id}",method=RequestMethod.GET)
	public @ResponseBody String getNotificationByUserId(@PathVariable("id") int user_id){
		return notificationService.getNotificationByUserId(user_id);
	}
	
	/*
	 * Find Notifications by Date
	 */
	
	@RequestMapping(value="/notification/date/{date}",method=RequestMethod.GET)
	public @ResponseBody String getNotificationByDate(@PathVariable("date") Date date){
		return notificationService.getNotificationByDate(date);
	}
	
	/*
	 * Find Notifications by Day Interval 
	 */
	
	@RequestMapping(value="/notification/day_interval/{days}",method=RequestMethod.GET)
	public @ResponseBody String getNotificationByDayInterval(@PathVariable("days") int days){
		return notificationService.getNotificationByDayInterval(days);
	}
	
	/*
	 * Find Notifications by Hour Interval
	 */
	@RequestMapping(value="/notification/hour_interval/{hours}",method=RequestMethod.GET)
	public @ResponseBody String getNotificationByHourInterval(@PathVariable("hours") int hours){
		return notificationService.getNotificationByHourInterval(hours);
	}
	
	@Autowired
	@Qualifier("notificationService")
	public void setNotificationService(NotificationService notificationService){
		this.notificationService = notificationService;
	}
	
}
