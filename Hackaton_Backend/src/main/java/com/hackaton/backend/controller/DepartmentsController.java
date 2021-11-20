package com.hackaton.backend.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hackaton.backend.model.DepartmentsDTO;
import com.hackaton.backend.repository.DepartmentsRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/departments")
public class DepartmentsController {
	
	@Autowired
	private DepartmentsRepository repo;
	
	@GetMapping("/list/{code_country}")
	public ArrayList<DepartmentsDTO> list(@PathVariable("code_country") long code_country){
		ArrayList<DepartmentsDTO> list = (ArrayList<DepartmentsDTO>) repo.findAll();
		list.removeIf( department -> (department.getCode_country_department() != code_country) );
		return list;
	}
	
}
