package org.smatmenu.DB;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.smartmenu.common.ParameterCatalog;
import org.smartmenu.model.Adds;
import org.smartmenu.model.DashboardItem;
import org.smartmenu.model.Order;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class OrderDaoImp extends BasicDAO<Order, ObjectId> implements OrderDao
{
	
	public OrderDaoImp(Class<Order> entityClass, Datastore ds) 
	{
		super(entityClass, ds);
	}
	
	
	public boolean addNewOrder(Order newItem)
	{
		boolean isAdded = false; 
		try 
		{
			// save order date 
			 Calendar c = Calendar.getInstance();	
			 c.setTime(new Date());	
			 c.add(Calendar.MONTH, 1);			 
			 newItem.setDate(c.get(Calendar.DAY_OF_MONTH)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR));
			 newItem.setOrder_time(c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE));			 
	     	getDatastore().save(newItem);
	    	isAdded =  true;
		}
		catch(Exception ex)
		{
			System.out.println("error while adding new Item");
		}
		
		return isAdded;
	}
	
	public List<Order> getOrdersByDay(String orderDate)
	{
		List <Order> results = new ArrayList();
		int size;
		
		try
		{	
			Query<Order> query = createQuery().field("date").equal(orderDate);
			
			results = query.asList();
			
			// Query<Order>  itemsQuery = createQuery().field("timestamp").greaterThanOrEq("ISODate("+"2017-02-08"+")");
//					 itemsQuery .and( itemsQuery.criteria("timestamp").greaterThanOrEq(formatter.format(date1)),
//					 itemsQuery.criteria("timestamp").lessThan(formatter.format(date2) ));
			
					 
			 
//			 BasicDBObject query = new BasicDBObject("timestamp", new BasicDBObject("$gte", "ISODate(" +"2017-02-08"+")"))
//				        .append("timestamp", new BasicDBObject("$lt", "ISODate("+"2017-02-09"+")"));
			
			 //= ("{"+ "timestamp"+":"+ "{"+ "$gte"+ ":" +"ISODate("+"2017-02-08" +"),"+"$lt"+":"+"ISODate("+"2017-02-09"+")}}";
					 
			
			//DBCursor cur = .find("{"+ "timestamp"+":"+ "{"+ "$gte"+ ":" +"ISODate("+"2017-02-08" +"),"+"$lt"+":"+"ISODate("+"2017-02-09"+")}}");
			 
//			BasicDBObject query = new BasicDBObject("orderDate" , new Date());
//			 
//			 size =  getDatastore().getCollection(Order.class).find(query).size();
			// results = itemsQuery.asList();
		}
		catch(Exception ex)
		{
			System.out.println("error while getting orders by day");
		}
		return results;		
	}
	
	public String DayIndexConvertor(int index)
	{
		String DayStr ="";
		switch(index)
		{
		case 0:
			DayStr= "Sunday";
		break;
		
		case 1:
			DayStr= "Monday";
			break;
		case 2:
			DayStr= "Tuesday";
			break;
		case 3:
			DayStr= "wednesday";
			break;
		case 4:
			DayStr= "Thursday";
			break;
		case 5:
			DayStr= "Friday";
			break;
		case 6:
			DayStr= "Saturday";
			break;				
	    }
		return DayStr;
	}

	
	
}
