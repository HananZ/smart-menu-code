package org.smatmenu.DB;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.smartmenu.model.RecommendedFromChef;
import org.smartmenu.model.Review;
import org.smartmenu.model.SubCategory;

public class ReviewDaoImp extends BasicDAO<Review, ObjectId> implements ReviewDao 
{
	
	public ReviewDaoImp(Class<Review> entityClass, Datastore ds) 
	{
		super(entityClass, ds);
	}
	public boolean addNewReview(Review newItem)
	{
		boolean isAdded = false; 
		try 
		{
		getDatastore().save(newItem);
		isAdded =  true;
		}
		catch(Exception ex)
		{
			System.out.println("error while adding new Item");
		}
		
		return isAdded;
	}
}
