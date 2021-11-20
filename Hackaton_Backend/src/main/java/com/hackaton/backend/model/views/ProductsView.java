package com.hackaton.backend.model.views;

import java.util.ArrayList;

import com.hackaton.backend.model.ImagesProductsDTO;
import com.hackaton.backend.model.ProductsDTO;

public class ProductsView {
	
	private ProductsDTO product;
	private ArrayList<ImagesProductsDTO> imgs;
	
	public ProductsView() {
		
	}
	
	public ProductsView(ProductsDTO product, ArrayList<ImagesProductsDTO> imgs) {
		super();
		this.product = product;
		this.imgs = imgs;
	}
	
	public ProductsDTO getProduct() {
		return product;
	}
	public void setProduct(ProductsDTO product) {
		this.product = product;
	}
	public ArrayList<ImagesProductsDTO> getImgs() {
		return imgs;
	}
	public void setImgs(ArrayList<ImagesProductsDTO> imgs) {
		this.imgs = imgs;
	}
	
}
