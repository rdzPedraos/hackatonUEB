package com.hackaton.backend.model;

import javax.persistence.*;
import java.util.Date;
//java.time.LocalDate
//now()

@Entity
@Table(name = "products")
public class ProductsDTO {
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Id
	@Column(name = "code")
	private int code;
	
	private int code_category_product;
	private int code_user_product;
	
	private String title;
	private String description;
	
	private double price;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private int in_use;

	public ProductsDTO(){
		
	}
	
	public ProductsDTO(int code, int code_category_product, int code_user_product, String title, String description,
			double price, Date date, int in_use) {
		super();
		this.code = code;
		this.code_category_product = code_category_product;
		this.code_user_product = code_user_product;
		this.title = title;
		this.description = description;
		this.price = price;
		this.date = date;
		this.in_use = in_use;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode_category_product() {
		return code_category_product;
	}

	public void setCode_category_product(int code_category_product) {
		this.code_category_product = code_category_product;
	}

	public int getCode_user_product() {
		return code_user_product;
	}

	public void setCode_user_product(int code_user_product) {
		this.code_user_product = code_user_product;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getIn_use() {
		return in_use;
	}

	public void setIn_use(int in_use) {
		this.in_use = in_use;
	}
}
