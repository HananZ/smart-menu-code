package org.smatmenu.DB;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;
import org.smartmenu.model.SubCategory;

public interface SubCategoryDao extends DAO<SubCategory, ObjectId> 
{
public boolean addNewSubCategory(SubCategory newItem);
}
