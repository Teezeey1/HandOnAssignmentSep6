package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="sale")
@Entity
public class Sale {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int item_id;
	
	String sold_by;
	String item_name;
	String item_description;
	float price;
	String sold_to;
	@Override
	public String toString() {
		return "Sale [item_id=" + item_id + ", sold_by=" + sold_by + ", item_name=" + item_name
				+ ", item_description=" + item_description + ", price=" + price + ", sold_to=" + sold_to + "]";
	}
	public Sale() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Sale(int item_id, String sold_by, String item_name, String item_description, float price, String sold_to) {
		super();
		this.item_id = item_id;
		this.sold_by = sold_by;
		this.item_name = item_name;
		this.item_description = item_description;
		this.price = price;
		this.sold_to = sold_to;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getSold_by() {
		return sold_by;
	}
	public void setSold_by(String sold_by) {
		this.sold_by = sold_by;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setSeller_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_description() {
		return item_description;
	}
	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getSold_to() {
		return sold_to;
	}
	public void setSold_to(String sold_to) {
		this.sold_to = sold_to;
	}
}
