EmailNotification
=================

This is a backend service for real time use case of an online site where a user can do multiple activities like list an item, buy an item, place a bid, win an auction item, buy a fixed price item etc. For every activity done on the site, user will receive a notification. Every notification  sent to the user will be recorded in the system. Every record will have the user id, user name, activity details and date/time of the notification, sent to the user. Used in-memory Caching wherever required.


Given that the application stores all the details related to user activity, it would be useful to expose this information to clients for consumption. 
Requirement :
Expose an interface which can be used  by clients for recording user activity
Expose the user activity details to clients 


Deliverables - 
REST api to record the user activity 
REST api to expose user activity based on 
 (1) UserId
 (2) Particular Date
 (3) Date range (say for 2 days, 3 hours etc. . )

Constraits -
Use NoSQL database MongoDB to store user activity




Database Connection - com.ebay.email.dao


Service - com.ebay.email.service/n


Controller - com.ebay.email/n




Notification Schema : 
create table notifications(
    notification_id:int
    user_id:int
    product_id:int
    activity:String('List','Buy','Bid')
    date:Date
  )

Similarly we can have 'Users' and 'Products' tables to extract user and product details.

Users Schema :
create table Users( 
    user_id:int
    name:String
    email:String
  )
  
Product Schema :
create table Product(
  product_id:int
  description:String
  price:double
  user_id:int
  type:String
  
)


