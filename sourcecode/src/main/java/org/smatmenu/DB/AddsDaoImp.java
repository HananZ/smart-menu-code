package org.smatmenu.DB;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.smartmenu.common.ErrorCode;
import org.smartmenu.common.ParameterCatalog;
import org.smartmenu.model.Adds;
import org.smartmenu.model.Category;


public class AddsDaoImp extends BasicDAO<Adds, ObjectId> implements AddsDao {
	public AddsDaoImp(Class<Adds> entityClass, Datastore ds) 
	{
		super(entityClass, ds);
	}
	
	public boolean addNewAdds(Adds newItem)
	{
		boolean isAdded = false; 
		try 
		{
			
			  if((createQuery().field("ar_text").equal(newItem.getAr_text()).get()== null) || 
					  (createQuery().field("en_text").equal(newItem.getEn_text()).get()== null) )
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
	
	public boolean editAdds(Adds updatedItem)
	{
		   boolean 	isUpdated = false ;	 
		   Query<Adds> itemsQuery ;
		 
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
