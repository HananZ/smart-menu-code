package org.smatmenu.DB;



import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;
import org.smartmenu.model.Category;
import org.smartmenu.model.SubCategory;


public interface CategoryDao extends DAO<Category, ObjectId>
{
	public boolean addNewCategory(Category newItem);
 List<Category> getAllItems();
}
