EmailNotification
=================

Database Connection - com.ebay.email.dao
Service - com.ebay.email.service
Controller - com.ebay.email




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


