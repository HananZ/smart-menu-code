package org.smatmenu.DB;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;
import org.smartmenu.model.Adds;
import org.smartmenu.model.Order;

public interface OrderDao  extends DAO<Order, ObjectId>
{
	

	public boolean addNewOrder(Order newItem);
}
