package org.smartmenu.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Restaurant 
{
	@Id 
	private String objectId ; 
	private String restaurant_id ; 
	private String Storage_name ; 
	private String token; 
	
	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getObjectId() 
	{
		return objectId;
	}
	
	public void setObjectId(String objectId) 
	{
		this.objectId = objectId;
	}
	
	public String getRestaurant_id() 
	{
		return restaurant_id;
	}
	
	public void setRestaurant_id(String restaurant_id) 
	{
		this.restaurant_id = restaurant_id;
	}
	
	public String getStorage_name()
	{
		return Storage_name;
	}
	
	public void setStorage_name(String storage_name)
	{
		Storage_name = storage_name;
	}

	
	
}
