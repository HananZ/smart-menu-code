package org.smatmenu.DB;



import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;
import org.smartmenu.model.Review;;

public interface ReviewDao extends DAO<Review, ObjectId> 
{


}
