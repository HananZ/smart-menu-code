package org.smatmenu.DB;
import org.smartmenu.model.*;
import org.mongodb.morphia.dao.DAO;
import org.bson.types.ObjectId;

public interface UserDao  extends DAO<User,ObjectId >
{
	
	public User getUserByCredentials (String UserName , String pw);

}
