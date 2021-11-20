package com.hackaton.backend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.hackaton.backend.DAO.ProductsServices;
import com.hackaton.backend.model.ProductsDTO;
import com.hackaton.backend.model.views.ProductsView;

@CrossOrigin(origins = "http://localhost:5000")
@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductsServices services;
	
	//Listamos los productos que no estén en uso:
	@GetMapping("/list")
	public ArrayList<ProductsView> list(){
		return services.list();
	}
	
	@GetMapping("/list/{type}/{id}")
	//listar productos de un usuario:
	public ArrayList<ProductsView> listAll(
		@PathVariable("type") String type,
		@PathVariable("id") long id
	){
		return services.list(id, type);
	}
	
	@PostMapping("/create")
	public JsonObject update(
			@RequestParam("code_category") int code_category,
			@RequestParam("code_user") int code_user,
			@RequestParam("title") String title,
			@RequestParam("description") String description,
			@RequestParam("price") double price,
			@RequestParam("available") int available,
			@RequestParam("file") MultipartFile[] files)
	{
		ProductsDTO product = new ProductsDTO(
			code_category, code_user, title, description, price, available
		);
		
		return services.create(product, files);
	}
	
	@DeleteMapping("/remove/{id}")
	public void remove(@PathVariable("id") int id) {
		services.delete(id);
	}
}
