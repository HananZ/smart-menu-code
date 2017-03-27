package org.smartmenu.model;


import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;




@Entity("Menu-Items-Dishes")
public class Dish
{
	@Id
	private String  objectId;	
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getAr_description() {
		return ar_description;
	}
	public void setAr_description(String ar_description) {
		this.ar_description = ar_description;
	}
	public Integer getCategory_ID() {
		return category_ID;
	}
	public void setCategory_ID(Integer category_ID) {
		this.category_ID = category_ID;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAr_title() {
		return ar_title;
	}
	public void setAr_title(String ar_title) {
		this.ar_title = ar_title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public String[] getPictures() {
		return pictures;
	}
	public void setPictures(String[] pictures) {
		this.pictures = pictures;
	}
	public ExtraOption[] getExtraOptions() {
		return extraOptions;
	}
	public void setExtraOptions(ExtraOption[] extraOptions) {
		this.extraOptions = extraOptions;
	}
	public StandardOption[] getStandardOptions() {
		return standardOptions;
	}
	public void setStandardOptions(StandardOption[] standardOptions) {
		this.standardOptions = standardOptions;
	}
	public Price[] getPrice() {
		return price;
	}
	public void setPrice(Price[] price) {
		this.price = price;
	}
	public boolean isRecommended() {
		return recommended;
	}
	public void setRecommended(boolean recommended) {
		this.recommended = recommended;
	}
	private String  ar_description;
	private Integer category_ID ;	
	private String Status ;	
	private long id; 
	private String guid; 
	private String thumb; 
	private String title; 
	private String ar_title; 
	private String body; 
	private String [] tags; 
	private String [] pictures; 
	private ExtraOption [] extraOptions;
	private StandardOption [] standardOptions;
	private Price [] price;
	private boolean recommended ; 

	
}
