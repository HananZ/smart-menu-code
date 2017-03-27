package org.smatmenu.DB;

import java.util.Locale.Category;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;
import org.smartmenu.model.Adds;


public interface AddsDao extends DAO<Adds, ObjectId>
{
	public boolean addNewAdds(Adds newItem);
}
