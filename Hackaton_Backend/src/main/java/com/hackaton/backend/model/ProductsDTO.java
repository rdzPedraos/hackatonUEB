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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long code;
	
	private int code_category_product;
	private int code_user_product;
	
	private String title;
	private String description;
	
	private double price;
	
	private int available;
	
	@Column(columnDefinition = "INT NOT NULL DEFAULT 0")
	private int not_available;

	public ProductsDTO(){
		
	}
	
	public ProductsDTO(int code_category_product, int code_user_product, String title, 
		String description, double price, int available
	) {
		super();
		this.code_category_product = code_category_product;
		this.code_user_product = code_user_product;
		this.title = title;
		this.description = description;
		this.price = price;
		this.available = available;
		not_available = 0;
	}

	public ProductsDTO(int code, int code_category_product, int code_user_product, String title, String description,
			double price, int available, int not_available) {
		super();
		this.code = code;
		this.code_category_product = code_category_product;
		this.code_user_product = code_user_product;
		this.title = title;
		this.description = description;
		this.price = price;
		this.available = available;
		this.not_available = not_available;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
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

	public int getavailable() {
		return available;
	}

	public void setavailable(int available) {
		this.available = available;
	}

	public int getNot_available() {
		return not_available;
	}

	public void setNot_available(int not_available) {
		this.not_available = not_available;
	}

	
}
