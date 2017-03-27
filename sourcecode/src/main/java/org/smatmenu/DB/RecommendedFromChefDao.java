package org.smatmenu.DB;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;
import org.smartmenu.model.RecommendedFromChef;;

public interface RecommendedFromChefDao extends DAO<RecommendedFromChef, ObjectId>
{
	public List<RecommendedFromChef> getAllItems();
}
