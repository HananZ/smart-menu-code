package org.smatmenu.DB;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;
import org.smartmenu.model.Promotion;;

public interface PromotionDao extends DAO<Promotion, ObjectId>
{
	
public List<Promotion>	getAllItems();

}
