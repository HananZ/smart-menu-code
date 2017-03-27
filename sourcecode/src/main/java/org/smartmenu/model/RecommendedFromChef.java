package org.smartmenu.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


@Entity("Recommended_From_Chef_Dishes")
public class RecommendedFromChef 
{
	
	
	@Id
	private String  objectId;
	private String  title_EN;
	private String  title_AR;
	private String  description_EN;
	private String  description_AR;
	private Integer Rate ;
	private String  Photo_URL ;
	private Integer price ; 
	private String  price_unit_AR;
	private String  price_unit_EN;
	private String  latest_Review ;
	private Integer Category_ID ;
	private Integer Sub_category_ID ;
	private String Status ;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	private long id;
	
	
	
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getTitle_EN() {
		return title_EN;
	}
	public void setTitle_EN(String title_EN) {
		this.title_EN = title_EN;
	}
	public String getTitle_AR() {
		return title_AR;
	}
	public void setTitle_AR(String title_AR) {
		this.title_AR = title_AR;
	}
	public String getDescription_EN() {
		return description_EN;
	}
	public void setDescription_EN(String description_EN) {
		this.description_EN = description_EN;
	}
	public String getDescription_AR() {
		return description_AR;
	}
	public void setDescription_AR(String description_AR) {
		this.description_AR = description_AR;
	}
	public Integer getRate() {
		return Rate;
	}
	public void setRate(Integer rate) {
		Rate = rate;
	}
	public String getPhoto_URL() {
		return Photo_URL;
	}
	public void setPhoto_URL(String photo_URL) {
		Photo_URL = photo_URL;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getPrice_unit_AR() {
		return price_unit_AR;
	}
	public void setPrice_unit_AR(String price_unit_AR) {
		this.price_unit_AR = price_unit_AR;
	}
	public String getPrice_unit_EN() {
		return price_unit_EN;
	}
	public void setPrice_unit_EN(String price_unit_EN) {
		this.price_unit_EN = price_unit_EN;
	}
	public String getLatest_Review() {
		return latest_Review;
	}
	public void setLatest_Review(String latest_Review) {
		this.latest_Review = latest_Review;
	}
	public Integer getCategory_ID() {
		return Category_ID;
	}
	public void setCategory_ID(Integer category_ID) {
		Category_ID = category_ID;
	}
	public Integer getSub_category_ID() {
		return Sub_category_ID;
	}
	public void setSub_category_ID(Integer sub_category_ID) {
		Sub_category_ID = sub_category_ID;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}

}
