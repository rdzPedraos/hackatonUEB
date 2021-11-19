package com.hackaton.backend.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hackaton.backend.model.ContractsDTO;
import com.hackaton.backend.repository.ContractsRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/contracts")
public class ContractsController {
	
	@Autowired
	private ContractsRepository repository;
	
	@GetMapping("/list/{id_user}")
	public ArrayList<ContractsDTO> list(@PathVariable("id_user") long id){
		
		//Obtenemos la fecha actual:
		Date dateNow = Calendar.getInstance().getTime();
		
		ArrayList<ContractsDTO> list = (ArrayList<ContractsDTO>) repository.findAll();
		//Comparamos para cada valor, si el contrato tiene una fecha de cancelación inferior a la actual.
		//Es decir, ya se venció.
		list.removeIf( 
			contract -> (
					contract.getDate_init().compareTo(dateNow) < 0 ||
					( //contract.getCode_user_owner_contract() != id && 
					  contract.getCode_user_lessee_contract() != id )
			)
		);
		return list;
	}
	
	/*
	@GetMapping("/listAll")
	public ArrayList<ContractsDTO> listAll(){
		ArrayList<ContractsDTO> list = (ArrayList<ContractsDTO>) repository.findAll();
		return list;
	}*/
	
	@PutMapping("/update")
	public void update(@RequestBody ContractsDTO user) {
		repository.save(user);
	}
	
	@DeleteMapping("/remove/{id}")
	public void remove(@PathVariable("id") long id) {
		repository.deleteById(id);
	}
}
