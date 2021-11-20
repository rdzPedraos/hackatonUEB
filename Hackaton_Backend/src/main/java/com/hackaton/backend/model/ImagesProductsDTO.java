package com.hackaton.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "images_products")
public class ImagesProductsDTO {
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long code;
	
	private long code_product_image;
	
	private String url;
	
	
	public ImagesProductsDTO() {
		
	}

	public ImagesProductsDTO(long code_product_image, String url) {
		super();
		this.code_product_image = code_product_image;
		this.url = url;
	}
	
	public ImagesProductsDTO(long code, int code_product_image, String url) {
		super();
		this.code = code;
		this.code_product_image = code_product_image;
		this.url = url;
	}


	public long getCode_product_image() {
		return code_product_image;
	}

	public void setCode_product_image(long code_product_image) {
		this.code_product_image = code_product_image;
	}

	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
