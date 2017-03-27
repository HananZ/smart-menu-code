package org.smartmenu.model;

import java.util.List;
import java.sql.*;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Order
{
	@Id 
	private String objectId; 
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Integer getOrder_ID() {
		return order_ID;
	}
	public void setOrder_ID(Integer order_ID) {
		this.order_ID = order_ID;
	}
	public Integer getItem_ID() {
		return item_ID;
	}
	public void setItem_ID(Integer item_ID) {
		this.item_ID = item_ID;
	}
	public List<Integer> getAdds_ID() {
		return Adds_ID;
	}
	public void setAdds_ID(List<Integer> adds_ID) {
		Adds_ID = adds_ID;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public Integer getTable_no() {
		return table_no;
	}
	public void setTable_no(Integer table_no) {
		this.table_no = table_no;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOrder_time() {
		return order_time;
	}
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}
	private String currency; 
	private String day; 
	private Integer order_ID;
	private Integer item_ID;		
	private List<Integer>Adds_ID ;
	private double total_price ;
	private Integer table_no;
	private String  date ;
	private String order_time; 
	
}
