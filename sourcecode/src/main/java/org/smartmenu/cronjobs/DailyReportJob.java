package org.smartmenu.cronjobs;
import java.util.ArrayList;
import java.math.BigInteger;
import java.security.*;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import  org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.smartmenu.model.DailyReport;
import org.smartmenu.model.Dish;
import org.smartmenu.model.Order;
import org.smartmenu.model.Restaurant;
import org.smartmenu.model.User;
import org.smatmenu.DB.OrderDaoImp;
import org.smatmenu.DB.UserDaoImp;



public class DailyReportJob implements  Job

{
	 @Override
     public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException 
	 {
		 // get rush hours from orders table			
		 List <Order> ordersList  = new ArrayList();
		 ReportsUtilities utilityObj = new ReportsUtilities("Master");
		for (Restaurant restObj :  utilityObj.getAllRestaurants())
		{
			
			  utilityObj = new ReportsUtilities(restObj.getRestaurant_id());
		 ordersList =  utilityObj.getOrdersFromTable(); 
		 Integer totalOrders = ordersList.size();
		 
		 String  rushHour ="";
		 
		 HashMap<String, Integer>  timeFrequency = new HashMap<String, Integer> ();
		 HashMap<Integer, Integer>  tableNoMap = new HashMap<Integer, Integer> ();
		 HashMap<Integer, Integer>  topDishesMap = new HashMap<Integer, Integer> ();
		  
		 double totalSales=0;		
		 Integer popular_tableNo =0;	
		 for (Order item: ordersList)
		 {
			 
			 
	
		// get popular table id 
		if (tableNoMap.size()!= 0 && tableNoMap.containsKey(item.getTable_no()) )
		{
			tableNoMap.put(item.getTable_no(),tableNoMap.get(item.getTable_no())+1);
		}
		else 
			tableNoMap.put(item.getTable_no(),1);	
		
		// get popular Dishes. 
		
		if (topDishesMap.size()!= 0 && topDishesMap.containsKey(item.getItem_ID()) )
		{
			topDishesMap.put(item.getItem_ID(),topDishesMap.get(item.getItem_ID())+1);
		}
		else 
			topDishesMap.put(item.getItem_ID(),1);	
		
		
		 // get rush hour
		String orderTime = item.getOrder_time();
		orderTime =orderTime.substring(0 , 2);
		if (timeFrequency.size()!= 0 && timeFrequency.containsKey(orderTime) )
		{
			timeFrequency.put(orderTime,timeFrequency.get(orderTime)+1);
		}
		else 
			timeFrequency.put(orderTime,1);	
		
		//
		
		// get total sales 		
		totalSales = item.getTotal_price() + totalSales;	
		 }
		 
		// determine rush hour. 
			Integer maxCount =0;
			for (Object value:timeFrequency.keySet())
			{
				
				if ( maxCount == 0 ||timeFrequency.get(value) > maxCount)
				{
					maxCount = timeFrequency.get(value);
					rushHour =  value.toString();
				}		
			}
			maxCount =0;
			for (Integer value:tableNoMap.keySet())
			{
				
				if ( maxCount == 0 ||tableNoMap.get(value) > maxCount)
				{
					maxCount = tableNoMap.get(value);
					popular_tableNo =  value;
				}		
			}
			

		    	// 1. Convert Map to List of Map
			   List<Map.Entry<Integer, Integer>> list =
		               new LinkedList<Map.Entry<Integer, Integer>>(topDishesMap.entrySet());
			   
			   // 2. Sort list with Collections.sort(), provide a custom Comparator
		       //    Try switch the o1 o2 position for a different order
		       Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
		           public int compare(Map.Entry<Integer, Integer> o1,
		                              Map.Entry<Integer, Integer> o2) {
		               return (o1.getValue()).compareTo(o2.getValue());
		           }
		           });
		     
		       // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
		       Map<Integer, Integer> sortedDishesList = new LinkedHashMap<Integer, Integer>();
		       for (Map.Entry<Integer, Integer> entry : list) {
		           sortedDishesList.put(entry.getKey(), entry.getValue());
		       }
		       
		       
		       // get dishes ids list  
		       Integer count =0;
		       List<Integer> dishesIdList = new ArrayList();
		       for (Integer entry : sortedDishesList.keySet())
		       {
		    	if ( count <= 5)
		    	{
		    		dishesIdList.add(entry);
		    	}
		    	else
		    		break;     	   
		       }
		       
		    List <Dish> popularDishesList =utilityObj.getPopularDishes(dishesIdList);
		    
		    
		    
	   	      Calendar c = Calendar.getInstance();	
			  c.setTime(new Date());
			  c.add(Calendar.MONTH,1);
			  
		      DailyReport reportObj = new DailyReport();
		      reportObj.setCurrency("SAR");   
		      reportObj.setDate(c.get(Calendar.DAY_OF_MONTH)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR));
		      reportObj.setDay(utilityObj.getDay(c.get(Calendar.DAY_OF_WEEK)));
		      reportObj.setMost_popular_table_id(popular_tableNo);
		    //  reportObj.setPopular_dish(popularDishesList);
		      reportObj.setRush_hours(rushHour);
		      reportObj.setTotal_orders(totalOrders);
		      reportObj.setTotal_sales(totalSales);      
		      utilityObj.saveReport(reportObj);
		 
		}  
	 }
	 
	  
     public static void main(String args[]) throws NoSuchAlgorithmException
     {
		
     }

}
