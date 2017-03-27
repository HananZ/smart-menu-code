package org.smartmenu.webservices;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


import org.smartmenu.common.ErrorCode;
import org.smartmenu.common.ParameterCatalog;
import org.smartmenu.common.Required;
import org.smartmenu.model.*;


import com.google.gson.Gson;
import javax.ws.rs.core.Response;


@Path("myresource")
public class MyResource {
	
	
	

/**
 * Method handling HTTP GET requests. The returned object will be sent
 * to the client as "text/plain" media type.
  * @return String that will be returned as a text/plain response.
 */
@GET
@Produces(MediaType.APPLICATION_JSON)
public String getIt() {
    return "test it!";
}
    
   
/**
this web service used to view  customer dash board items 
@param none
@return dash board items
 */
 @GET
 @Path("getDashBoardItems")
 @Produces(MediaType.APPLICATION_JSON)
  public Response getDashboardItems(@NotNull @QueryParam("token") String token  , @NotNull @QueryParam("restaurantId") String restaurantId  )
  { 	
	 
	 Hashtable result = new Hashtable (); 
	 try
	 {	
    // initialize new object from web utilities class 
     webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);
     
     if (utilityObj.tokenCheck (token ,restaurantId))
 	{
    // String restaurantId = utilityObj.getRestaurantId(token);     
	 List<DashboardItem> dashboarTableData = new ArrayList();		 
	 // get all data , store in  a list
	 dashboarTableData = utilityObj.getAllItemsFromDashboardItemsTable();
	// result = getCategoriesFunction(utilityObj); 
	 result.put("DashboardItems", dashboarTableData);
	 // convert  output to json.
	 return Response.ok(new Gson().toJson(result), MediaType.APPLICATION_JSON).build();		
	 }	
	 else
	 {
 		return Response.status(Response.Status.FORBIDDEN).entity("Unauthorized Request").build();
     }  
	 }
	 catch ( Exception ex )
	 {		
	 return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.GET_DASHBOARD_ITEMS_ERROR).build();		
	 } 
  }
 
 
 
 @GET
 @Path("getCategories")
 @Produces(MediaType.APPLICATION_JSON)
  public Response getCategories( @NotNull @QueryParam("restaurantId") String restaurantId  , @NotNull @QueryParam("token") String token)
  { 	 
	 try
	 {	 
		 Hashtable results  = new Hashtable(); 
		 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId); 
         //webServicesUtilities utilityObj = new webServicesUtilities("Master");  
		 if (utilityObj.tokenCheck (token ,restaurantId))
	    	{
			    results = getCategoriesFunction(utilityObj); 
			    // convert output to json.	
				 return Response.ok(new Gson().toJson(results), MediaType.APPLICATION_JSON).build();
	    	}
	    	else
	    	{
	    		return Response.status(Response.Status.FORBIDDEN).entity("Unauthorized Request").build();
	    	}    	 
    
	 	
	 }
	 catch ( Exception ex )
	 {		
	 return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.GET_DASHBOARD_ITEMS_ERROR).build();
		
	 } 
  }
 
 /**
 this web service used to view  selected category data 
 @param category descriptiveUrl 
 @return category json data 
  */
  @GET
  @Path("viewSelectedItemData")
  @Produces(MediaType.APPLICATION_JSON) 
   public Response viewSelectedDashbaordData(@NotNull @QueryParam(ParameterCatalog.DASHBOARD_ITEM_URL) String descriptiveUrl ,@NotNull @QueryParam("token") String token ,  @NotNull @QueryParam("restaurantId") String restaurantId) 
   { 
	  Hashtable results  = new Hashtable(); 
 	 try
 	 { 	 		 
 	 DashboardItem selectedItem = null; 
     webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);
     List<Promotion> promotionList  = null;
     List<Dish> recommendedList= null;	  
      
     if (utilityObj.tokenCheck (token ,restaurantId))
 	{
 			// if promotion category is required , call get promotion method , store it in the result Map
 			 if (descriptiveUrl.equals(ParameterCatalog.PROMOTIONS_URI))
 			{
 				promotionList =	utilityObj.getPromotions();
 				results.put("promotion",promotionList);
 		     	
 			} 			 
 			// if recommended from chef  category is required , call get recommended method , store it in the result Map
 			if (descriptiveUrl.equals(ParameterCatalog.RECOMMENDED_FROM_CHEF_URI))
 			  { 				 
 				recommendedList = utilityObj.getRecommendedFromChefDishes();
 				results.put("recommended",recommendedList);
 				
 			  } 			 
 			 // in case if the requested category is most rated or most popular : get menu from dish collection  
 			 else
 			 {
 				recommendedList=	utilityObj.getCategoryMenuFromDishesTable(descriptiveUrl );
 				results.put("main", recommendedList); 				
 			 }
 
      return Response.ok(new Gson().toJson(results), MediaType.APPLICATION_JSON).build();
 	}
     else
     {
    	 return Response.status(Response.Status.FORBIDDEN).entity("Unauthorized Request").build();	 
     }
 	 }
 	 catch ( Exception ex )
 	 {
 	 return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.GET_CATEGORY_MENU_DATA).build();		
 	 } 
   }
 
  
  /**
  this web service used to activate dashboard item
  @param dashboard item descriptiveUrl 
  @return status message 
   */
   @GET
   @Path("activate")   
   
    public Response activateDashboardItems(String descriptive_Uri , @NotNull @QueryParam("restaurantId") String restaurantId)
    {
	    webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	 
		 if( utilityObj.activateDashboardItem(descriptive_Uri))
		   return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_ACTIVATION).build();
		 else
			 return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.ACTIVATE_DASHBOARD_ITEM).build(); 
	     	 
    }
   
   /**
   this web service used to deactivate dashboard item
   @param dashboard item descriptiveUrl 
   @return status message 
    */
    @GET
    @Path("deactivate")   
     public Response deactivateDashboardItems(String descriptive_Uri , @NotNull @QueryParam("restaurantId") String restaurantId)    
     {
 	    webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	 
 		 if( utilityObj.deactivateDashboardItem(descriptive_Uri))
 		   return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
 		 else
 			 return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build(); 
 	     	 
     }   
    
    
    /**
    this web service used to add new dish to the menu
    @param dish gson  
    @return status message 
     */
    @POST 
    @Path("add/dish")
    public Response addnewMenuItem(Dish newDish , @NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj. addNewDish(newDish);    	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    /**
    this web service used to add new category to the menu
    @param category gson  
    @return status message 
     */
    @POST 
    @Path("add/category")
    public Response addnewCategory(Category newCategory ,@NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.addNewCategory(newCategory);    	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
   
    
    /**
    this web service used to add new sub category to the menu
    @param sub category gson  
    @return status message 
     */
    @POST 
    @Path("add/subcategory")
    public Response addnewSubCategory(SubCategory newSubCategory ,@NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.addNewSubCategory(newSubCategory);    	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    
    /**
    this web service used to add new adds to the menu
    @param adds gson  
    @return status message 
     */
    @POST 
    @Path("add/newAdds")
    public Response addnewAdds(Adds newAdd , @NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.addNewAdds(newAdd);    	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    /**
    this web service used to add new adds to the menu
    @param adds gson  
    @return status message 
     */
    @POST 
    @Path("add/order")
    public Response addnewOrder(Order newOrder ,  @NotNull @HeaderParam("restaurantId") String restaurantId ,@NotNull @HeaderParam("token") String token)
    {
    	try 
    	{   
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);
    	if (utilityObj.tokenCheck (token ,restaurantId))
    	{
    		utilityObj.addNewOrder(newOrder);  
    		return Response.status(Response.Status.OK).entity("order is sent successfully").build();
    	}
    	else
    	{
    		return Response.status(Response.Status.FORBIDDEN).entity("Unauthorized Request").build();
    	}
    	 
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
   
    /**
    this web service used to add new review
    @param adds gson  
    @return status message 
     */
    @POST 
    @Path("add/review")
    public Response addnewReview(Review newReview ,@NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.addNewReview(newReview)    ;	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    /**
    this web service used to add new recommended dishes from chef
    @param adds gson  
    @return status message 
     */
    @POST 
    @Path("add/recommended")
    public Response addnewReview(RecommendedFromChef newRecommendedChef , @NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.addNewRecommendedFromChefDish(newRecommendedChef)  ;	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    @POST 
    @Path("add/promotion")
    public Response addnewPromotion( Promotion proItem , @NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.addNewPromotion(proItem);	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    /**
    this web service used to update dish in the menu
    @param dish gson  
    @return status message 
     */
    @POST 
    @Path("edit/dish")
    public Response updateMenuItem(Dish updateItem , @NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.editDish(updateItem);    	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    /**
    this web service used to update category in the menu
    @param category gson  
    @return status message 
     */
    @POST 
    @Path("edit/category")
    public Response updateCategory(Category updateItem , @NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.editCategory(updateItem);    	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    /**
    this web service used to update category in the menu
    @param category gson  
    @return status message 
     */
    @POST 
    @Path("edit/adds")
    public Response updateAdds(Adds updateItem , @NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.editAdds(updateItem);    	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    /**
    this web service used to update category in the menu
    @param category gson  
    @return status message 
     */
    @POST 
    @Path("edit/promotion")
    public Response updateAdds(Promotion updateItem , @NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.editPromotion(updateItem);    	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    /**
    this web service used to update category in the menu
    @param category gson  
    @return status message 
     */
    @POST 
    @Path("edit/recommended")
    public Response updateAdds(RecommendedFromChef updateItem ,@NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.editRecommendedFromChef(updateItem);    	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    /**
    this web service used to update category in the menu
    @param category gson  
    @return status message 
     */
    @POST 
    @Path("edit/subcategory")
    public Response updateAdds(SubCategory updateItem , @NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.editSubCategory(updateItem);    	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    
    /**
    this web service used to update category in the menu
    @param category gson  
    @return status message 
     */
    @POST 
    @Path("delete/dish")
    public Response deleteDish(Dish deletedItem , @NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.deleteDish(deletedItem);    	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    
    
    /**
    this web service used to update category in the menu
    @param category gson  
    @return status message 
     */
    @POST 
    @Path("delete/adds")
    public Response deleteAdds(Adds deletedItem , @NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.deleteAdds(deletedItem);    	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    
    /**
    this web service used to update category in the menu
    @param category gson  
    @return status message 
     */
    @POST 
    @Path("delete/category")
    public Response deleteCategory(Category deletedItem , @NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.deleteCategory(deletedItem);    	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    /**
    this web service used to update category in the menu
    @param category gson  
    @return status message 
     */
    @POST 
    @Path("delete/promotion")
    public Response deletePromotion(Promotion deletedItem , @NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId) ; 
	
    	 utilityObj.deletePromotion(deletedItem);    	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    /**
    this web service used to update category in the menu
    @param category gson  
    @return status message 
     */
    @POST 
    @Path("delete/recommended")
    public Response deleteRecommendedDish(RecommendedFromChef deletedItem , @NotNull @QueryParam("restaurantId") String restaurantId )
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.deleteRecommendedFromChef(deletedItem);    	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    /**
    this web service used to update category in the menu
    @param category gson  
    @return status message 
     */
    @POST 
    @Path("delete/subcategory")
    public Response deleteSubCategory(SubCategory deletedItem, @NotNull @QueryParam("restaurantId") String restaurantId)
    {
    	try 
    	{
    	 webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);	
    	 utilityObj.deleteSubCategory(deletedItem);    	
    	 return Response.status(Response.Status.OK).entity(ErrorCode.SUCCESSFUL_DEACTIVATION).build();
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    
    @GET
    @Path("getOrders")
    @Produces(MediaType.APPLICATION_JSON)
     public Response getOrders( @NotNull @QueryParam("restaurantId") String restaurantId)
     { 	 
   	 try
   	 {	
   		 
   		 Hashtable results  = new Hashtable(); 
   		 
   		 
   	 // initialize new object from web utilities class 
        webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);     
   	 List<Order> TableData = new ArrayList();	
   	 
   	 // get all data , store in  a list
   	 TableData = utilityObj.getAllOrders();	 
   	 
   	 
   	 results.put("id", 1);
   	 results.put("jsonrpc", "2.0");
   	 results.put( "total", 5);
   	 results.put( "result", TableData);
   	 results.put("timestamp", new Timestamp(new Date().getTime()));
   	
        // convert output to json.	
   	 return Response.ok(new Gson().toJson(results), MediaType.APPLICATION_JSON).build();	
   	
   	 }
   	 catch ( Exception ex )
   	 {		
   	 return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.GET_DASHBOARD_ITEMS_ERROR).build();
   		
   	 } 
     }
    
    
    /**
    this web service used to update category in the menu
    @param category Json  
    @return status message 
     */
    @GET
    @Path("Admin/login")
    public Response getAdminLoginDetails(@NotNull @QueryParam("userName")String userName , @NotNull @QueryParam("passWord")  String passWord )
    {    	
      try 
    	{
    	  
    	  Hashtable results  = new Hashtable();
    	  String RestaurantId= userName.substring(userName.indexOf("@")+1);
    	  webServicesUtilities utilityObj = new webServicesUtilities(RestaurantId); 
    	  User loginUser = utilityObj.checkUser(userName ,passWord);
    	 if (loginUser != null)
    	 {  
    		 //results = getCategoriesFunction(utilityObj);
    		 results.put("token", loginUser.getToken());
    		 results.put("Restaurant_id",loginUser.getRestaurant_id());
    		 results.put("user_name" , loginUser.getUser_name());
    		 results.put("role" , loginUser.getRole());
    		 return Response.ok(new Gson().toJson(results), MediaType.APPLICATION_JSON).build();
    	 }
    	 else
    	 {
    		 return Response.status(Response.Status.FORBIDDEN).entity("wronge credintials").build();
    	 }
    	  
    	  
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    } 
    
    
    /**
    this web service used to update category in the menu
    @param category Json  
    @return status message 
     */
    @GET
    @Path("client/login")
    public Response getClientLoginDetails(@NotNull @QueryParam("token")String token , @NotNull @QueryParam("restaurantId")  String restaurantId )
    {    	
      try 
    	{
    	  
    	  Hashtable results  = new Hashtable();    	
    	  webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);     	  
      	      	     
    		 List<DashboardItem> dashboarTableData = new ArrayList();   
    		 
    		 // get all data , store in  a list
    		 dashboarTableData = utilityObj.getAllItemsFromDashboardItemsTable();    		
    		 results.put("dashboard_items", dashboarTableData);
    		// results.put("Restaurant_id",currentRestaurant.getRestaurant_id());
    		 return Response.ok(new Gson().toJson(results), MediaType.APPLICATION_JSON).build();
    	
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    public Hashtable getCategoriesFunction( webServicesUtilities  utilityObj)
    {
    	 Hashtable results  = new Hashtable();      	    
    	 List<Category> TableData = new ArrayList();
    	 
    	 // get all data , store in  a list
    	 TableData = utilityObj.getAllItemsFromCategoryItemsTable();	 
    	 results.put("id", 1);
    	 results.put("jsonrpc", "2.0");
    	 results.put( "total", TableData.size());
    	 results.put( "categories", TableData);
    	 results.put("timestamp", new Timestamp(new Date().getTime()));    	 
    	 return results;
    }
    
    /**
    this web service used to update category in the menu
    @param category Json  
    @return status message 
     */
    @GET
    @Path("getReports")
    @Produces(MediaType.APPLICATION_JSON)
   
    public Response getReportsData(@NotNull @QueryParam("token")String token , @NotNull @QueryParam("restaurantId")  String restaurantId , @NotNull @QueryParam("startDate")String startDate ,@NotNull @QueryParam("endDate") String endDate )
    {    	
      try 
    	{
    	  
    	  Hashtable results  = new Hashtable();    	
    	  webServicesUtilities utilityObj = new webServicesUtilities(restaurantId);     	  
          
    	  if (utilityObj.adminTokenCheck(token ,restaurantId))
    	 	{    	  
                 	     
    		 List<DailyReport> dailyReportList = new ArrayList();   
    		 
    		 // get all data , store in  a list
    		 dailyReportList = utilityObj.getReportsData (startDate , endDate );    		
    		 results.put("dailyReports", dailyReportList);
    		return Response.ok(new Gson().toJson(results), MediaType.APPLICATION_JSON).build();
    	 }
    	 else
    	 {
    		 return Response.status(Response.Status.FORBIDDEN).entity("wronge credintials").build();
    	 } 
    	}
    	catch(Exception ex)
    	{
    		return Response.status(Response.Status.NOT_FOUND).entity(ErrorCode.DEACTIVATE_DASHBOARD_ITEM).build();
    	}
    }
    
    
}



 
 
 
 
 

    
    
