package org.smartmenu.model;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Adds 
{
@Id 
private String objectId ; 
private long id ;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
private String ar_text;
private String en_text;
private List<Integer> itemsIds;


public String getObjectId() {
	return objectId;
}
public void setObjectId(String objectId) {
	this.objectId = objectId;
}

public String getAr_text() {
	return ar_text;
}
public void setAr_text(String ar_text) {
	this.ar_text = ar_text;
}
public String getEn_text() {
	return en_text;
}
public void setEn_text(String en_text) {
	this.en_text = en_text;
}
public List<Integer> getItemsIds() {
	return itemsIds;
}
public void setItemsIds(List<Integer> itemsIds) {
	this.itemsIds = itemsIds;
}

}
