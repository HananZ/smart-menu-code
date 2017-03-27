package org.smartmenu.model;

import java.util.List;

import org.mongodb.morphia.annotations.Id;

import com.mongodb.DBObject;

public class DailyReport
{
	
	@Id 
	private String objectId; 
	private long timestamp ; 
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getRush_hours() {
		return rush_hours;
	}
	public void setRush_hours(String rush_hours) {
		this.rush_hours = rush_hours;
	}
	public double getTotal_sales() {
		return total_sales;
	}
	public void setTotal_sales(double total_sales) {
		this.total_sales = total_sales;
	}
	public Integer getMost_popular_table_id() {
		return most_popular_table_id;
	}
	public void setMost_popular_table_id(Integer most_popular_table_id) {
		this.most_popular_table_id = most_popular_table_id;
	}
	public Integer getTotal_orders() {
		return total_orders;
	}
	public void setTotal_orders(Integer total_orders) {
		this.total_orders = total_orders;
	}
	public List<DBObject> getPopular_dish() {
		return popular_dish;
	}
	public void setPopular_dish(List<DBObject> popular_dish) {
		this.popular_dish = popular_dish;
	}
	
	private String day ; 
	private String date ;
	private String Currency ;
	private String rush_hours ;
	private double total_sales ;
	private Integer most_popular_table_id ;
	private Integer total_orders ;
	private List<DBObject> popular_dish ;
	
	
	
	
	
	
	
	
}
