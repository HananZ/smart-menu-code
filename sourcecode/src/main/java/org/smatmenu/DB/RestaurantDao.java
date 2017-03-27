package org.smatmenu.DB;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.dao.DAO;
import org.smartmenu.model.DashboardItem;
import org.smartmenu.model.Restaurant;

public interface RestaurantDao extends DAO<Restaurant, ObjectId> 
{
	public List<Restaurant> getAllRestaurants();

}
