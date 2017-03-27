package org.smartmenu.model;

import java.util.Hashtable;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


@Entity("Reviews")
public class Review 
{
	
	private Integer Item_ID;
	private Hashtable<String, Object> review;
	
	@Id
	private String  objectId;
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public Integer getItem_ID() {
		return Item_ID;
	}
	public void setItem_ID(Integer item_ID) {
		Item_ID = item_ID;
	}
	public Hashtable<String, Object> getReview() {
		return review;
	}
	public void setReview(Hashtable<String, Object> review) {
		this.review = review;
	}

	


}
