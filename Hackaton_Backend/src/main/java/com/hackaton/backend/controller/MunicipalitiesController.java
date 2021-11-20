package com.hackaton.backend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hackaton.backend.model.MunicipalitiesDTO;
import com.hackaton.backend.repository.MunicipalitiesRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/municipalities")
public class MunicipalitiesController {
	@Autowired
	private MunicipalitiesRepository repo;
	
	@GetMapping("/list/{code_department}")
	public ArrayList<MunicipalitiesDTO> list(@PathVariable("code_department") long code_department){
		ArrayList<MunicipalitiesDTO> list = (ArrayList<MunicipalitiesDTO>) repo.findAll();
		list.removeIf( department -> (department.getCode_department_municipality() != code_department) );
		return list;
	}
}
