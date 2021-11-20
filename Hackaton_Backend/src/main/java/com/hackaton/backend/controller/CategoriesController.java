package com.hackaton.backend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hackaton.backend.model.CategoriesDTO;
import com.hackaton.backend.repository.CategoriesRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/categories")
public class CategoriesController {
	@Autowired
	private CategoriesRepository repo;
	
	@GetMapping("/list")
	public ArrayList<CategoriesDTO> list(){
		return (ArrayList<CategoriesDTO>) repo.findAll();
	}
}
