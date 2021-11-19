package com.hackaton.backend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hackaton.backend.model.ImagesProductsDTO;
import com.hackaton.backend.repository.ImagesProductsRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/images")
public class ImagesProductsController {
	
	@Autowired
	private ImagesProductsRepository repository;
	
	@GetMapping("/list/{id}")
	public ArrayList<ImagesProductsDTO> list(@PathVariable("id") long id){
		ArrayList<ImagesProductsDTO> list = (ArrayList<ImagesProductsDTO>) repository.findAll();
		list.removeIf( image -> (image.getCode_product_image() != id) );
		return list;
	}
	
	@PutMapping("/update")
	public void update(@RequestBody ImagesProductsDTO user) {
		repository.save(user);
	}
	
	@DeleteMapping("/remove/{id}")
	public void remove(@PathVariable("id") long id) {
		repository.deleteById(id);
	}
}
