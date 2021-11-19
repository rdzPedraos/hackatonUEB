package com.hackaton.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "images_products")
public class ImagesProductsDTO {
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int code;
	
	private int code_product_image;
	private String url;
	private String description;
	
	public ImagesProductsDTO() {
		
	}
	

	public ImagesProductsDTO(int code, int code_product_image, String url, String description) {
		super();
		this.code = code;
		this.code_product_image = code_product_image;
		this.url = url;
		this.description = description;
	}


	public int getCode_product_image() {
		return code_product_image;
	}

	public void setCode_product_image(int code_product_image) {
		this.code_product_image = code_product_image;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}