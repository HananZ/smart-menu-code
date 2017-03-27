package org.smatmenu.DB;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.smartmenu.common.ErrorCode;
import org.smartmenu.model.Category;
import org.smartmenu.model.DailyReport;
import org.smartmenu.model.DashboardItem;

public class DailyReportDaoImp extends BasicDAO<DailyReport, ObjectId> implements DailyReportDao
{

	
	public DailyReportDaoImp(Class<DailyReport> entityClass, Datastore ds) 
	{
		super(entityClass, ds);
	}
	
	public boolean addNewReport(DailyReport newItem)
	{
		boolean isAdded = false; 
		try 
		{
//			
		getDatastore().save(newItem);
		isAdded =  true;
			
		}
		catch(Exception ex)
		{
			System.out.println("error while adding new Item");
		}
		return isAdded;
	}
	
	public List<DailyReport> getReportsByDate(String startDate , String endDate)
	{
		Query<DailyReport>  itemsQuery = null;
		try
		{
			
			
			DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
			Date formattedStartDate = df.parse(startDate);
			Date formattedEndDate =  df.parse(endDate);
			
			
			Date formatdddtedEndDate =  df.parse("11/2/2017");
	    	
	    	Date formattedddEndDate =  df.parse("16/2/2017");
	    	
			 
	            itemsQuery = createQuery();
				itemsQuery.and(
						itemsQuery.criteria("timestamp").greaterThanOrEq(formattedStartDate.getTime()),
						
						itemsQuery.criteria("timestamp").lessThanOrEq(formattedEndDate.getTime()));				
		}
		catch(Exception ex)
		{
			System.out.println(ErrorCode.GET_DASHBOARD_ITEMS_ERROR);
		}		
		return itemsQuery.asList();
	}
	
}
