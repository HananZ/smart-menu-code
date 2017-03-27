package org.smatmenu.DB;

import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.*;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.smartmenu.model.Order;
import org.smartmenu.model.Restaurant;
import com.mongodb.DBCollection;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

public class RestaurantDaoImp  extends BasicDAO<Restaurant, ObjectId> implements RestaurantDao
{
	public List<Restaurant> getAllRestaurants()
	{
	return	getDatastore().createQuery(Restaurant.class).asList();
	}
	public RestaurantDaoImp(Class<Restaurant> entityClass, Datastore ds) 
	{
		super(entityClass, ds);
	}
	
	public Restaurant getRestaurantByToken ( String token)
	{
		Restaurant currentRestaurant = null ; 
		
		Query<Restaurant> resQuery= createQuery().field("token").equal(token); 
		
		currentRestaurant = resQuery.get(); 
		
		return currentRestaurant;
		}
}
