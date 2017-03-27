package org.smatmenu.DB;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.smartmenu.common.ErrorCode;
import org.smartmenu.model.RecommendedFromChef;
import org.smartmenu.model.SubCategory;

public class SubCategoryDaoImp extends BasicDAO<SubCategory, ObjectId> implements SubCategoryDao 
{
	public SubCategoryDaoImp(Class<SubCategory> entityClass, Datastore ds) 
	{
		super(entityClass, ds);
	}
	
	public boolean addNewSubCategory(SubCategory newItem)
	{
		boolean isAdded = false; 
		try 
		{
			
			 if((createQuery().field("Name_AR").equal(newItem.getName_AR()).get()== null) || 
					  (createQuery().field("Name_EN").equal(newItem.getName_EN()).get()== null) )
			  {
		getDatastore().save(newItem);
		isAdded =  true;
		}
		}
		catch(Exception ex)
		{
			System.out.println("error while adding new Item");
		}
		
		return isAdded;
	}
	
	public boolean editSubCategory(SubCategory updatedItem)
	{
		   boolean 	isUpdated = false ;	 
		   Query<SubCategory> itemsQuery ;
		 
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
