package org.smatmenu.DB;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.smartmenu.common.ErrorCode;
import org.smartmenu.common.ParameterCatalog;
import org.smartmenu.model.DashboardItem;
import org.smartmenu.model.Dish;


public class DishDaoImp extends BasicDAO<Dish, ObjectId> implements DishDao 
{
	
	public DishDaoImp(Class<Dish> entityClass, Datastore ds) 
	{
		super(entityClass, ds);
	}	
	
	public Dish getDishByObjectId(String objectId)
	{
		    Query<Dish>  itemsQuery = createQuery().field(ParameterCatalog.OBJECTID).equal(objectId);
			return itemsQuery.get();
	}
	public Dish getDishById(long id)
	{
		    Query<Dish>  itemsQuery = createQuery().field("id").equal(id);
			return itemsQuery.get();
	}
	
	public Dish getDishByEnglishTitle(String title_EN)
	{
		Query<Dish>  itemsQuery = createQuery().field(ParameterCatalog.DISH_TITLE_EN).equal(title_EN);
		return itemsQuery.get();
	}
	public Dish getDishByArabicTitle(String title_AR)
	{
		Query<Dish>  itemsQuery = createQuery().field(ParameterCatalog.DISH_TITLE_AR).equal(title_AR);
		return itemsQuery.get();
	}
	
	public Dish getDishByArabicDescription(String description_AR)
	{
		Query<Dish>  itemsQuery = createQuery().field(ParameterCatalog.DISH_DESCRIPTION_AR).equal(description_AR);
		return itemsQuery.get();
	}
	
	public Dish getDishByRate(Integer rate)
	{
		Query<Dish>  itemsQuery = createQuery().field(ParameterCatalog.DISH_RATE).equal(rate);
		return itemsQuery.get();
	}
	public Dish getDishbyPhoto(String photoUrl)
	{
		Query<Dish>  itemsQuery = createQuery().field(ParameterCatalog.DISH_PHOTO_URL).equal(photoUrl);
		return itemsQuery.get();
	}
	public Dish getDishByPrice(Integer price)
	{
		Query<Dish>  itemsQuery = createQuery().field(ParameterCatalog.DISH_PRICE).equal(price);
		return itemsQuery.get();
	}
	
	public Dish getDishByLatestReview(String review)
	{
		Query<Dish>  itemsQuery = createQuery().field(ParameterCatalog.DISH_LATEST_REVIEW).equal(review);
		return itemsQuery.get();
	}
	public List<Dish> getDishByCategoryId(Integer categoryId)
	{
		Query<Dish>  itemsQuery = null;
		List<Dish> dishesList = new ArrayList();
		
		try
		{
	            itemsQuery = createQuery();
				itemsQuery.and(												
						itemsQuery.criteria("Status").equal("active"),
						itemsQuery.criteria("category_ID").equal(categoryId));
				dishesList = itemsQuery.asList();
		}
		catch(Exception ex)
		{
			System.out.println(ErrorCode.GET_DASHBOARD_ITEMS_ERROR);
		}		
		 return dishesList ;	
	}
	public Dish getDishBySubCategory(Integer subCategoryId)
	{
		Query<Dish>  itemsQuery = createQuery().field(ParameterCatalog.DISH_SUB_CATEGORY_ID).equal(subCategoryId);
		return itemsQuery.get();
	}
	
	public Dish getDishByStatus(String status)
	{
		Query<Dish>  itemsQuery = createQuery().field(ParameterCatalog.STATUS).equal(status);
		return itemsQuery.get();	
	}
	public List<Dish> getAllDishes()
	{
		 Datastore ds = getDatastore();
	     return ds.createQuery(Dish.class).asList();
	}
	public List<Dish> getDishByDescriptiveId (String DescriptiveId )
	{
		
		List<Dish> dishes = new ArrayList();		
		try
		{
		 
		Query<Dish>  itemsQuery;
		
		 switch (DescriptiveId)
		 {
         case ParameterCatalog.MAIN_MENU_URI:
         {
        	
         	 itemsQuery = createQuery().field(ParameterCatalog.STATUS).equal("active");
         	 dishes.addAll(itemsQuery.asList());
             break;
         } 
         case ParameterCatalog.MOST_POPULAR_URI:
         {
        	 itemsQuery = createQuery().field(ParameterCatalog.STATUS).equal("active")
        			 .field(ParameterCatalog.DISH_ORDERES_COUNT)
        			 .greaterThan(0).order("-"+ParameterCatalog.DISH_ORDERES_COUNT).limit(5);
        	 dishes.addAll(itemsQuery.asList());
                  break;
         }
         case ParameterCatalog.MOST_RATED_URI:
         {
        	 itemsQuery = createQuery().field(ParameterCatalog.STATUS).equal("active")
        			 .field(ParameterCatalog.DISH_RATE)
        			 .greaterThanOrEq(4).order("-"+ParameterCatalog.DISH_RATE);  
        	 dishes.addAll(itemsQuery.asList());
                  break;
         }
        
          
	}
		 
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage() + ErrorCode.GET_CATEGORY_MENU_DATA);		
		}
		
		return dishes;
		 
	}
	
	
	public boolean addNewDish (Dish newDish)
	{
	   boolean 	isaAdded = false ;
	
	   
	   try
	   {
		   
		  if((createQuery().field(ParameterCatalog.DISH_TITLE_AR).equal(newDish.getAr_title()).get()== null) || 
				  (createQuery().field(ParameterCatalog.DISH_TITLE_EN).equal(newDish.getTitle()).get()== null) )
		  {
			  
	   newDish.setId(getDatastore().getCount(Dish.class)+1); 
	   getDatastore().save(newDish);
	   isaAdded= true;
		  }
		  else
		  {
			  System.out.println("dish exists");
		  }
		 
	   }
	   catch(Exception e)
		{
			System.out.println(e.getMessage() + ErrorCode.GET_CATEGORY_MENU_DATA);		
		}
	   return isaAdded;
	   
		
	}
	
	
	public boolean editDish (Dish updatedItem)
	{
	   boolean 	isUpdated = false ;	 
	   Query<Dish> itemsQuery ;
	 
	   try
	   {
		   
	  
	   itemsQuery = createQuery().field("id").equal(updatedItem.getId()); 
	  if ( itemsQuery.get() != null)
	  {
		  getDatastore().delete(itemsQuery.get()) ;
		  getDatastore().save(updatedItem);
		  isUpdated= true;
	  }  
	   
	   }
	   catch(Exception e)
	   {
			System.out.println(e.getMessage() + ErrorCode.GET_CATEGORY_MENU_DATA);		
	   }	   
	   return isUpdated;   
	} 
	
	
	
	public List<Dish> getRecommendedDishes()
	{
		Query<Dish>  itemsQuery = null;
		List<Dish> dishesList = new ArrayList();
		
		try
		{
	            itemsQuery = createQuery();
				itemsQuery.and(
						itemsQuery.criteria("recommended").equal(true),						
						itemsQuery.criteria("Status").equal("active"));
						//itemsQuery.criteria("category_ID").equal(categoryId));
				        dishesList = itemsQuery.asList();
		}
		catch(Exception ex)
		{
			System.out.println(ErrorCode.GET_DASHBOARD_ITEMS_ERROR);
		}		
		 return dishesList ;	
	}
	
	
}
