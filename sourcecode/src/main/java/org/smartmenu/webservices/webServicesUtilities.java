package org.smartmenu.webservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import java.util.Map;

import org.smartmenu.common.ErrorCode;
import org.smartmenu.model.Adds;
import org.smartmenu.model.Category;
import org.smartmenu.model.DailyReport;
import org.smartmenu.model.DashboardItem;
import org.smartmenu.model.Dish;
import org.smartmenu.model.Order;
import org.smartmenu.model.Promotion;
import org.smartmenu.model.RecommendedFromChef;
import org.smartmenu.model.Restaurant;
import org.smartmenu.model.Review;
import org.smartmenu.model.SubCategory;
import org.smartmenu.model.User;
import org.smatmenu.DB.AddsDaoImp;
import org.smatmenu.DB.CategoryDaoImp;
import org.smatmenu.DB.DailyReportDaoImp;
import org.smatmenu.DB.DashboardDaoImp;
import org.smatmenu.DB.DishDaoImp;
import org.smatmenu.DB.MorphiaService;
import org.smatmenu.DB.OrderDaoImp;
import org.smatmenu.DB.PromotionDaoImp;
import org.smatmenu.DB.RecommendedFromChefDaoImp;
import org.smatmenu.DB.RestaurantDaoImp;
import org.smatmenu.DB.ReviewDaoImp;
import org.smatmenu.DB.SubCategoryDaoImp;
import org.smatmenu.DB.UserDaoImp;

import com.google.gson.Gson;
import com.mongodb.DBObject;



public class webServicesUtilities 
{
	

	private MorphiaService morphiaObj ; 

	
	
	// constructor
	webServicesUtilities(String restaurantId)
	{	
		this.morphiaObj = new MorphiaService(restaurantId);
	}
	
	
	/**
	 * Method gets all rows from dashboard items table 
	  * @return dashboard items list 
	 */
	public List<DashboardItem> getAllItemsFromDashboardItemsTable ()
	{
		List<DashboardItem> TableDocuments= new ArrayList<DashboardItem>();
	  try
		{
		DashboardDaoImp _dao = new DashboardDaoImp(DashboardItem.class, morphiaObj.getDatastore());		
		TableDocuments.addAll(_dao.getAllItems());
		
		}
	  catch(Exception ex)
	  {
		 System.out.println(ErrorCode.GET_DASHBOARD_ITEMS_ERROR);
	  }
	  return TableDocuments;	    
		
	}
	
	
	public List<Category> getAllItemsFromCategoryItemsTable ()
	{
		List<Category> TableDocuments= new ArrayList<Category>();
	  try
		{
		  CategoryDaoImp _dao = new CategoryDaoImp(Category.class, morphiaObj.getDatastore());		
		TableDocuments.addAll(_dao.getAllItems());
		
		}
	  catch(Exception ex)
	  {
		 System.out.println(ErrorCode.GET_DASHBOARD_ITEMS_ERROR);
	  }
	  return TableDocuments;	    
		
	}
	
	/**
	 * Method gets menu from dish table (most popular/rated/main menu categories) 
	  * @return dishes Map list
	 */
	public List<Dish> getCategoryMenuFromDishesTable (String DashboardItemUrl)
	{	
		List<Dish> menuDishes = new ArrayList(); 
		try
		{
		DishDaoImp _dishDao = new DishDaoImp(Dish.class, morphiaObj.getDatastore());
		
		menuDishes.addAll(_dishDao.getAllDishes());
		
		}
		catch(Exception e )
		{
			System.out.println(ErrorCode.GET_CATEGORY_MENU_DATA);
			
		}
		
		return menuDishes;
	}
	
	/**
	 * Method gets menu from recommended from chef table 
	  * @return dishes Map list
	 */
	public List<Dish> getRecommendedFromChefDishes ()
	{
		
		
		 List<Dish> resultList = new ArrayList();
		try
//		{
		{
//			RecommendedFromChefDaoImp _dao = new RecommendedFromChefDaoImp(RecommendedFromChef.class, morphiaObj.getDatastore());
//			resultList= _dao.getAllItems();			
			DishDaoImp dishDao  = new DishDaoImp (Dish.class , morphiaObj.getDatastore());
			resultList = 	dishDao.getRecommendedDishes();
			
			
		}
		catch(Exception e)
		{
		    System.out.println(ErrorCode.GET_CATEGORY_MENU_DATA);
		}		
		return resultList;		
	}
	
	/**
	 * Method gets menu from promotion table 
	  * @return dishes Map list
	 */
	public List<Promotion> getPromotions ( )
	{		
		List<Promotion> result = new ArrayList();
		try
		{
			PromotionDaoImp _PromotionDao = new PromotionDaoImp(Promotion.class, morphiaObj.getDatastore());
			result = _PromotionDao.getAllItems();
		}
		catch(Exception ex)
		{
			 System.out.println(ErrorCode.GET_CATEGORY_MENU_DATA);
		}
		
		return result;
	}
	
	public boolean activateDashboardItem(String descriptive_uri)
	{
		boolean isActivated = false;
		try	
		{
			DashboardDaoImp _dao = new DashboardDaoImp(DashboardItem.class, morphiaObj.getDatastore());	
			if (_dao.activateDashboardItem(descriptive_uri))
				isActivated = true;			
		}
		catch(Exception ex)
		{
			 System.out.println(ErrorCode.ACTIVATE_DASHBOARD_ITEM);
		}
		return isActivated;
	}
	
	public boolean deactivateDashboardItem(String descriptive_uri)
	{
		boolean isDeactivated = false;
		try	
		{
			DashboardDaoImp _dao = new DashboardDaoImp(DashboardItem.class, morphiaObj.getDatastore());	
			if (_dao.deactivateDashboardItem(descriptive_uri))
				isDeactivated = true;			
		}
		catch(Exception ex)
		{
			 System.out.println(ErrorCode.DEACTIVATE_DASHBOARD_ITEM);
		}
		return isDeactivated;
	}
	
	public boolean addNewDish ( Dish newDish)
	{
		boolean isAdded= false;
		DishDaoImp _dao = new DishDaoImp(Dish.class, morphiaObj.getDatastore());	
		if (_dao.addNewDish(newDish))
			isAdded = true;
		return isAdded;		
	}
	
	public boolean addNewCategory ( Category newItem)
	{
		boolean isAdded= false;
		CategoryDaoImp _dao = new CategoryDaoImp(Category.class, morphiaObj.getDatastore());	
		if (_dao.addNewCategory(newItem))
			isAdded = true;
		return isAdded;
		
	}
	
	public boolean addNewSubCategory ( SubCategory newItem)
	{
		boolean isAdded= false;
		SubCategoryDaoImp _dao = new SubCategoryDaoImp(SubCategory.class, morphiaObj.getDatastore());	
		if (_dao.addNewSubCategory(newItem))
			isAdded = true;
		return isAdded;
		
	}
	
	public boolean addNewAdds ( Adds newItem)
	{
		boolean isAdded= false;
		AddsDaoImp _dao = new AddsDaoImp(Adds.class, morphiaObj.getDatastore());	
		if (_dao.addNewAdds(newItem))
			isAdded = true;
		return isAdded;
	}
	public boolean addNewOrder ( Order newItem)
	{
		boolean isAdded= false;
		OrderDaoImp _dao = new OrderDaoImp(Order.class, morphiaObj.getDatastore());	
		if (_dao.addNewOrder(newItem))
			isAdded = true;
		return isAdded;
	}
	public boolean addNewReview ( Review newItem)
	{
		boolean isAdded= false;
		ReviewDaoImp _dao = new ReviewDaoImp(Review.class, morphiaObj.getDatastore());	
		if (_dao.addNewReview(newItem))
			isAdded = true;
		return isAdded;
	}
	
	public boolean addNewPromotion( Promotion newItem)
	{
		boolean isAdded= false;
		PromotionDaoImp _dao = new PromotionDaoImp(Promotion.class, morphiaObj.getDatastore());	
		if (_dao.addNewPromotion(newItem))
			isAdded = true;
		return isAdded;
	}
	
	public boolean addNewRecommendedFromChefDish( RecommendedFromChef newItem)
	{
		boolean isAdded= false;
		RecommendedFromChefDaoImp _dao = new RecommendedFromChefDaoImp(RecommendedFromChef.class, morphiaObj.getDatastore());	
		if (_dao.addNewRecommendedToChefDish(newItem))
			isAdded = true;
		return isAdded;
	}
	
	
	public boolean editDish( Dish updatedItem)
	{
		boolean isUpdated= false;
		DishDaoImp _dao = new DishDaoImp(Dish.class, morphiaObj.getDatastore());	
		if (_dao.editDish(updatedItem))
			isUpdated = true;
		return isUpdated;
	}
	
	public boolean editCategory( Category updatedItem)
	{
		boolean isUpdated= false;
		CategoryDaoImp _dao = new CategoryDaoImp(Category.class, morphiaObj.getDatastore());	
		if (_dao.editCategory(updatedItem))
			isUpdated = true;
		return isUpdated;
	}
	
	public boolean editPromotion( Promotion updatedItem)
	{
		boolean isUpdated= false;
		PromotionDaoImp _dao = new PromotionDaoImp(Promotion.class, morphiaObj.getDatastore());	
		if (_dao.editPromotion(updatedItem))
			isUpdated = true;
		return isUpdated;
	}
	
	public boolean editRecommendedFromChef( RecommendedFromChef updatedItem)
	{
		boolean isUpdated= false;
		RecommendedFromChefDaoImp _dao = new RecommendedFromChefDaoImp(RecommendedFromChef.class, morphiaObj.getDatastore());	
		if (_dao.editRecommendedFromChefDish(updatedItem))
			isUpdated = true;
		return isUpdated;
	}
	
	public boolean editSubCategory( SubCategory updatedItem)
	{
		boolean isUpdated= false;
		SubCategoryDaoImp _dao = new SubCategoryDaoImp(SubCategory.class, morphiaObj.getDatastore());	
		if (_dao.editSubCategory(updatedItem))
			isUpdated = true;
		return isUpdated;
	}
	public boolean editAdds( Adds updatedItem)
	{
		boolean isUpdated= false;
		AddsDaoImp _dao = new AddsDaoImp(Adds.class, morphiaObj.getDatastore());	
		if (_dao.editAdds(updatedItem))
			isUpdated = true;
		return isUpdated;
	}
	
	public boolean deleteDish( Dish DeletedItem)
	{
		boolean isDeleted= false;
		DishDaoImp _dao = new DishDaoImp(Dish.class, morphiaObj.getDatastore());	
		_dao.delete(DeletedItem);
			isDeleted = true;
		return isDeleted;
	}
	
	public boolean deleteAdds( Adds DeletedItem)
	{
		boolean isDeleted= false;
		AddsDaoImp _dao = new AddsDaoImp(Adds.class, morphiaObj.getDatastore());	
		_dao.delete(DeletedItem);
			isDeleted = true;
		return isDeleted;
	}
	
	public boolean deleteCategory( Category DeletedItem)
	{
		boolean isDeleted= false;
		CategoryDaoImp _dao = new CategoryDaoImp(Category.class, morphiaObj.getDatastore());	
		_dao.delete(DeletedItem);
			isDeleted = true;
		return isDeleted;
	}
	
	public boolean deletePromotion( Promotion DeletedItem)
	{
		boolean isDeleted= false;
		PromotionDaoImp _dao = new PromotionDaoImp(Promotion.class, morphiaObj.getDatastore());	
		_dao.delete(DeletedItem);
			isDeleted = true;
		return isDeleted;
	}
	
	public boolean deleteRecommendedFromChef( RecommendedFromChef DeletedItem)
	{
		boolean isDeleted= false;
		RecommendedFromChefDaoImp _dao = new RecommendedFromChefDaoImp(RecommendedFromChef.class, morphiaObj.getDatastore());	
		_dao.delete(DeletedItem);
			isDeleted = true;
		return isDeleted;
	}
	public boolean deleteSubCategory( SubCategory DeletedItem)
	{
		
		boolean isDeleted= false;
		try {
			
			SubCategoryDaoImp _dao = new SubCategoryDaoImp(SubCategory.class, morphiaObj.getDatastore());	
			_dao.delete(DeletedItem);
				isDeleted = true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return isDeleted;
	}
	
	public List<Order> getAllOrders()
	{
		List<Order> TableDocuments= new ArrayList<Order>();
		OrderDaoImp _dao = new OrderDaoImp(Order.class, morphiaObj.getDatastore());	
		_dao.getOrdersByDay("jh");
			
		return TableDocuments;
	}
	
   public User checkUser (String userName , String passWord)
   {
	   User userObj = null; 
	   
	   try
	   {
		
		 UserDaoImp _dao = new  UserDaoImp(User.class , morphiaObj.getDatastore());
		 userObj = _dao.getUserByCredentials(userName, passWord) ;	   
	   
	   }
	   catch(Exception ex)
	   {
		   System.out.println(ErrorCode.ACTIVATE_DASHBOARD_ITEM);  
	   }	   
	   return userObj ; 
   }
	
   
 public String getRestaurantId (String token)
 {
	 
	 String  restaurantId = "";
	 try {
		
		 UserDaoImp _dao = new  UserDaoImp(User.class , morphiaObj.getDatastore());
		 restaurantId =  _dao.getUserByToken(token).getRestaurant_id(); 		 
         }
	 catch (Exception e) {		
		e.printStackTrace();
    	}
	return restaurantId ; 	 
 }
	
 
	
 public Restaurant getRestaurantByToken (String token)
 {
	 
	 Restaurant  currentRestaurant = null;
	 try {
		
		 RestaurantDaoImp _dao = new  RestaurantDaoImp(Restaurant.class , morphiaObj.getDatastore());
		 currentRestaurant =  _dao.getRestaurantByToken(token); 		 
         }
	 catch (Exception e) {		
		e.printStackTrace();
    	}
	return currentRestaurant ; 	 
 }
 
 
 public DashboardItem getDashboardItem (String dashboardItemUrl)
 {
	 
	 DashboardItem  selectedDashBoardItem = null;
	 try {
		
		 DashboardDaoImp _dao = new  DashboardDaoImp(DashboardItem.class , morphiaObj.getDatastore());
		 selectedDashBoardItem =  _dao.getItemByUrl(dashboardItemUrl); 		 
         }
	 catch (Exception e) {		
		e.printStackTrace();
    	}
	return selectedDashBoardItem ; 	 
 }
 
 
 public boolean tokenCheck (String token ,String restuarantId ) 
 {
	 boolean isValid = false; 
	 try {
		UserDaoImp usersDao = new UserDaoImp(User.class , morphiaObj.getDatastore()); 
		 User currentUser =   usersDao.getUserByToken(token);
		 if (currentUser != null)
			 if (currentUser.getRestaurant_id().equals(restuarantId))
				 isValid = true ;
	}
	 catch (Exception e)
	 {		
		e.printStackTrace();
	 } 	 
	 return isValid ;
 }
 

 
 public boolean adminTokenCheck (String token ,String restuarantId ) 
 {
	 boolean isValid = false; 
	 try {
		UserDaoImp usersDao = new UserDaoImp(User.class , morphiaObj.getDatastore()); 
		 User currentUser =   usersDao.getUserByToken(token);
		 if (currentUser != null)
			 if (currentUser.getRestaurant_id().equals(restuarantId) && currentUser.getRole().equals("ADMIN"))
				 isValid = true ;
	}
	 catch (Exception e)
	 {		
		e.printStackTrace();
	 } 	 
	 return isValid ;
 }
 
 public List<DailyReport> getReportsData (String startDate ,String endDate ) 
 {
	List<DailyReport> requestedData= new ArrayList(); 
    
	 try 
	 {
		 DailyReportDaoImp reportsDao  = new DailyReportDaoImp(DailyReport.class , morphiaObj.getDatastore()); 
	     reportsDao.getReportsByDate(startDate, endDate);		
	 }
	 catch (Exception e)
	 {		
		e.printStackTrace();
	 }
	 
	 return requestedData ;
	 
 }

 
 
 
 
}
		

	


