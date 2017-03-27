package org.smatmenu.DB;


import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;
import org.smartmenu.model.DashboardItem;

public interface DashboardDao extends DAO<DashboardItem, ObjectId>
{	
	public DashboardItem getItemByOrder(Integer order);
	public DashboardItem getItemByObjectId(Integer objectid);
	public DashboardItem getItemByUrl(String url);
	public DashboardItem getItemByEnglishText(String englishText);
	public DashboardItem getItemByArabicText(String arabicText);
	public List<DashboardItem> getAllItems();
}
	
	

