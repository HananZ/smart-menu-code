package org.smatmenu.DB;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;
import org.smartmenu.model.Dish;;

public interface DishDao extends DAO<Dish, ObjectId>
{
	public Dish getDishByObjectId(String objectId);
	public Dish getDishByEnglishTitle(String title_EN);
	public Dish getDishByArabicTitle(String title_AR);
	public Dish getDishByArabicDescription(String description_AR);
	public Dish getDishByRate(Integer rate);
	public Dish getDishbyPhoto(String photoUrl);
	public Dish getDishByPrice(Integer price);
	public Dish getDishByLatestReview(String review);
	public List<Dish> getDishByCategoryId(Integer categoryId);
	public Dish getDishBySubCategory(Integer subCategoryId);	
	public Dish getDishByStatus(String status);
	public Dish getDishById(long id);
	public List<Dish> getAllDishes();
	

}
