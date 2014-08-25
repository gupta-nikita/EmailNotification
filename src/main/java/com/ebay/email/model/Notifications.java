package com.ebay.email.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Notifications implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int notification_id;
	private int user_id;
	private int product_id;
	private String activity;
	private Date date;
	
	public Notifications(int user_id,int product_id,String activity,Date date){
		this.user_id = user_id;
		this.product_id = product_id;
		this.activity = activity;
		this.date = date;
	}
	//private Timestamp date_added;
	
//	public int getId() {
//		return _id;
//	}
//	public void setId(int id) {
//		this._id = id;
//	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
//	public Timestamp getDate_added() {
//		return date_added;
//	}
//	public void setDate_added(Timestamp date_added) {
//		this.date_added = date_added;
//	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNotification_id() {
		return notification_id;
	}

	public void setNotification_id(int notification_id) {
		this.notification_id = notification_id;
	}
	
	

}
