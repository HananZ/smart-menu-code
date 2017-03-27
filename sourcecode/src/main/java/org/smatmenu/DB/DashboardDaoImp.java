package org.smatmenu.DB;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.smartmenu.common.ErrorCode;
import org.smartmenu.common.ParameterCatalog;
import org.smartmenu.model.DashboardItem;


public class DashboardDaoImp extends BasicDAO<DashboardItem, ObjectId> implements DashboardDao 
{
	
	public DashboardDaoImp(Class<DashboardItem> entityClass, Datastore ds) 
	{
		super(entityClass, ds);
	}
	public DashboardItem getItemByOrder(Integer order)
	{
	    Query<DashboardItem>  itemsQuery = createQuery().field(ParameterCatalog.DASHBOARD_ITEM_ORDER).equal(order);
		return itemsQuery.get();
	}
	
	public DashboardItem getItemByObjectId(Integer objectid)
	{
		  Query<DashboardItem>  itemsQuery = createQuery().field(ParameterCatalog.OBJECTID).equal(objectid);
		  return itemsQuery.get();
	}
	
	public DashboardItem getItemByUrl(String url)
	{
		Query<DashboardItem>  itemsQuery = null;
		try
		{
	            itemsQuery = createQuery();
				itemsQuery.and(
						itemsQuery.criteria(ParameterCatalog.DASHBOARD_ITEM_URL).equal(url),						
						itemsQuery.criteria("Status").equal("active"));				
		}
		catch(Exception ex)
		{
			System.out.println(ErrorCode.GET_DASHBOARD_ITEMS_ERROR);
		}		
		 return itemsQuery.get();
	}
	public DashboardItem getItemByEnglishText(String englishText)
	{
		Query<DashboardItem>  itemsQuery = createQuery().field(ParameterCatalog.DASHBOARD_ITEM_ENG_TEXT).equal(englishText);
		return itemsQuery.get();
	}
	public DashboardItem getItemByArabicText(String arabicText)
	{
		Query<DashboardItem>  itemsQuery = createQuery().field(ParameterCatalog.DASHBOARD_ITEM_AR_TEXT).equal(arabicText);
		return itemsQuery.get();
	}
	public List<DashboardItem> getAllItems()
	{
		Query<DashboardItem>  itemsQuery = createQuery().field("Status").equal("active");
		return itemsQuery.asList();
	}
	public boolean activateDashboardItem(String descriptive_uri)
	{
		boolean isActivated= false;		
	     Query<DashboardItem>  itemsQuery = createQuery().field(ParameterCatalog.DASHBOARD_ITEM_URL).equal(descriptive_uri);
		 itemsQuery.get().setStatus(ParameterCatalog.ACTIVE_STATUS);
		 isActivated = true;
		
		 return isActivated;
		 
	}
	
	public boolean deactivateDashboardItem(String descriptive_uri)
	{
		 boolean isDeactivated= false;		
	     Query<DashboardItem>  itemsQuery = createQuery().field(ParameterCatalog.DASHBOARD_ITEM_URL).equal(descriptive_uri);
		 itemsQuery.get().setStatus(ParameterCatalog.INACTIVE_STATUS);
		 isDeactivated = true;		
		 return isDeactivated;
		 
	}
	
	public List<DashboardItem> getItemByRestaurantId(String restaurantId)
	{
		Query<DashboardItem>  itemsQuery = null;
		try
		{
	            itemsQuery = createQuery();
				itemsQuery.and(
						itemsQuery.criteria("restaurant_id").equal(restaurantId),
						
						itemsQuery.criteria("Status").equal("active"));				
		}
		catch(Exception ex)
		{
			System.out.println(ErrorCode.GET_DASHBOARD_ITEMS_ERROR);
		}		
		return itemsQuery.asList();
	}
}
