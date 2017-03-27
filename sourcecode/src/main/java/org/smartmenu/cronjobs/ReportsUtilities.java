package org.smartmenu.cronjobs;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.smartmenu.common.ErrorCode;
import org.smartmenu.model.DailyReport;
import org.smartmenu.model.Dish;
import org.smartmenu.model.Order;
import org.smartmenu.model.Restaurant;
import org.smartmenu.model.User;
import org.smatmenu.DB.DailyReportDaoImp;
import org.smatmenu.DB.DishDaoImp;
import org.smatmenu.DB.MorphiaService;
import org.smatmenu.DB.OrderDaoImp;
import org.smatmenu.DB.RestaurantDaoImp;
import org.smatmenu.DB.UserDaoImp;

public class ReportsUtilities 
{
	private MorphiaService morphiaObj ; 
	OrderDaoImp _dao ;
	DishDaoImp  _dishDao; 
	DailyReportDaoImp _reportsDao;
	
	public ReportsUtilities (String restaurantId)
	{
		
	     this.morphiaObj = new MorphiaService(restaurantId);	
		_dao = new OrderDaoImp(Order.class, morphiaObj.getDatastore());
		_dishDao = new DishDaoImp(Dish.class , morphiaObj.getDatastore());
		_reportsDao = new DailyReportDaoImp(DailyReport.class ,morphiaObj.getDatastore() );
		
	}
	
	
	public List<Order> getOrdersFromTable( )
	{
		 
		 List<Order> ordersList= new ArrayList();
		 
		 try
		 {	
		  Calendar c = Calendar.getInstance();	
		  c.setTime(new Date());	
		  c.add(Calendar.MONTH,1);
		  ordersList =  _dao.getOrdersByDay(c.get(Calendar.DAY_OF_MONTH)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR));
		 
		 }
		 catch(Exception ex)
		 {
			 System.out.println(ErrorCode.GET_DASHBOARD_ITEMS_ERROR);
		 }
		 
		 
		 return ordersList;
	}
	
	
	
	
	public List<Dish> getPopularDishes(List<Integer> dishIdsList)
	{
       List<Dish> dishesList= new ArrayList();
		 
       
		 try
		 {
	for (Integer id : dishIdsList)
	{
		dishesList.add(_dishDao.getDishById(id));
	} 
		 }
		 catch(Exception ex)
		 {
			 System.out.println(ErrorCode.GET_DASHBOARD_ITEMS_ERROR);
		 }
		 
		 
		 return dishesList;
		
		
	}
	
	
	public boolean saveReport(DailyReport  newItem)
	{
		boolean isAdded = false ; 
		
		try
		{
		_reportsDao.addNewReport(newItem);
		isAdded= true;
		}
		catch(Exception e)
		{
			 System.out.println(ErrorCode.GET_DASHBOARD_ITEMS_ERROR);
		}
		return isAdded ; 		
	}
	
	public String getDay (Integer dayIndex)
	{
		
		String day = "";
		switch (dayIndex)
		{
		case 0:
			day= "SUNDAY";
			break;
		case 1:
			day= "MONDAY";
			break;
		case 2:
			day= "TUESDAY";
			break;
		case 3:
			day= "WEDNESDAY";
			break;
		case 4:
			day= "THURSDAY";
			break;
		case 5:
			day= "FRIDAY";
			break;
		case 6:
			day= "SATURDAY";
			break;
		}
		return day; 
	}
	
	
	public List<Restaurant> getAllRestaurants()
	{	
		List<Restaurant> allRestaurants = new ArrayList<Restaurant>();		
		try 
		{
			RestaurantDaoImp resObj = new RestaurantDaoImp(Restaurant.class ,morphiaObj.getDatastore()); 
			allRestaurants =	resObj.getAllRestaurants();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		return allRestaurants;
	}
	
}
