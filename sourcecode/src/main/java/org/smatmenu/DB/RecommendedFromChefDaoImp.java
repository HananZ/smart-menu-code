package org.smatmenu.DB;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.smartmenu.common.ErrorCode;
import org.smartmenu.model.Promotion;
import org.smartmenu.model.RecommendedFromChef;


public class RecommendedFromChefDaoImp extends BasicDAO<RecommendedFromChef, ObjectId> implements RecommendedFromChefDao 
{
	public RecommendedFromChefDaoImp(Class<RecommendedFromChef> entityClass, Datastore ds) 
	{
		super(entityClass, ds);
	}
	public List<RecommendedFromChef> getAllItems()
	{
   
   	 Datastore ds = getDatastore();
   	 return ds.createQuery(RecommendedFromChef.class).asList();	 
    }
	public boolean addNewRecommendedToChefDish(RecommendedFromChef newItem)
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
	public boolean editRecommendedFromChefDish(RecommendedFromChef updatedItem)
	{
		   boolean 	isUpdated = false ;	 
		   Query<RecommendedFromChef> itemsQuery ;
		 
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

}
