package org.smatmenu.DB;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;
import org.smartmenu.model.Adds;
import org.smartmenu.model.DailyReport;

public interface DailyReportDao extends DAO<DailyReport, ObjectId>
{
	public boolean addNewReport(DailyReport newItem);
	

}
