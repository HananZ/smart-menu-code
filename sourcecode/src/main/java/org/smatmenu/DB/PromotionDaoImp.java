package org.smatmenu.DB;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.smartmenu.common.ErrorCode;
import org.smartmenu.model.Category;
import org.smartmenu.model.Dish;
import org.smartmenu.model.Promotion;
import org.smartmenu.model.SubCategory;

public class PromotionDaoImp extends BasicDAO<Promotion, ObjectId> implements PromotionDao {

	public PromotionDaoImp(Class<Promotion> entityClass, Datastore ds) 
	{
		super(entityClass, ds);
	}
	
	public List<Promotion>	getAllItems()
	{
		 Datastore ds = getDatastore();
	     return ds.createQuery(Promotion.class).asList();
	}
	public boolean addNewPromotion(Promotion newItem)
	{
		boolean isAdded = false; 
		try 
		{
		getDatastore().save(newItem);
		isAdded =  true;
		}
		catch(Exception ex)
		{
			System.out.println("error while adding new Item");
		}
		
		return isAdded;
	}
	
	public boolean editPromotion(Promotion updatedItem)
	{
		   boolean 	isUpdated = false ;	 
		   Query<Promotion> itemsQuery ;
		 
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
	
	public List<Promotion> getPromotionByCategoryId( Integer categoryId)
	{
		Query<Promotion>  itemsQuery = null;
		List<Promotion> promotionList = new ArrayList();
		
		try
		{
	            itemsQuery = createQuery();
				itemsQuery.and(												
						itemsQuery.criteria("Status").equal("active"),
						itemsQuery.criteria("category_ID").equal(categoryId));
				promotionList = itemsQuery.asList();
		}
		catch(Exception ex)
		{
			System.out.println(ErrorCode.GET_DASHBOARD_ITEMS_ERROR);
		}		
		 return promotionList ;	
	}
}
