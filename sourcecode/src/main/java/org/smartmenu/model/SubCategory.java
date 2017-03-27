package org.smartmenu.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity 
public class SubCategory 
{
	@Id
	private String objectId; 
	private Integer Category_ID;
	private long id ;
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	private String Name_AR;
    private String Name_EN;
    private String Status;
    
	public String getObjectId()
	{
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public Integer getCategory_ID() {
		return Category_ID;
	}
	public void setCategory_ID(Integer category_ID) {
		Category_ID = category_ID;
	}

	public String getName_AR() {
		return Name_AR;
	}
	public void setName_AR(String name_AR) {
		Name_AR = name_AR;
	}
	public String getName_EN() {
		return Name_EN;
	}
	public void setName_EN(String name_EN) {
		Name_EN = name_EN;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
}
