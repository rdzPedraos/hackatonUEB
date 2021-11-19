package com.hackaton.backend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hackaton.backend.model.ProductsDTO;
import com.hackaton.backend.repository.ProductsRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/products")
public class ProductsController {
	@Autowired
	private ProductsRepository repository;
	
	@GetMapping("/list/{id_user}")
	public ArrayList<ProductsDTO> list(@PathVariable("id") long id){
		ArrayList<ProductsDTO> list = (ArrayList<ProductsDTO>) repository.findAll();
		list.removeIf( user -> (user.getIn_use() == 1 || user.getCode_user_product() != id) );
		return list;
	}
	
	@GetMapping("/listAll/{id_user}")
	public ArrayList<ProductsDTO> listAll(@PathVariable("id") long id){
		ArrayList<ProductsDTO> list = (ArrayList<ProductsDTO>) repository.findAll();
		list.removeIf( user -> ( user.getCode_user_product() != id ) );
		return list;
	}
	
	@PutMapping("/update")
	public void update(@RequestBody ProductsDTO user) {
		repository.save(user);
	}
	
	@DeleteMapping("/remove/{id}")
	public void remove(@PathVariable("id") long id) {
		repository.deleteById(id);
	}
}
