package org.smatmenu.DB;



import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.smartmenu.common.ErrorCode;
import org.smartmenu.model.Category;
import org.smartmenu.model.DashboardItem;
import org.smartmenu.model.Dish;


public class CategoryDaoImp extends BasicDAO<Category, ObjectId> implements CategoryDao
{
	public CategoryDaoImp(Class<Category> entityClass, Datastore ds) 
	{
		super(entityClass, ds);
	}
	
	public boolean addNewCategory(Category newItem)
	{
//		boolean isAdded = false; 
//		try 
//		{
////			 if((createQuery().field("Name_AR").equal(newItem.getName_AR()).get()== null) || 
////					  (createQuery().field("Name_EN").equal(newItem.getName_EN()).get()== null) )
////			  {
//		getDatastore().save(newItem);
//		isAdded =  true;
//			  }
//		}
//		catch(Exception ex)
//		{
//			System.out.println("error while adding new Item");
//		}
//		
		return true;
	}
	
	public boolean editCategory(Category updatedItem)
	{
		   boolean 	isUpdated = false ;	 
		   Query<Category> itemsQuery ;
		 
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
	
	public List<Category> getAllItems()
	{
		 Datastore ds = getDatastore();
	     return ds.createQuery(Category.class).asList();
	}
}
