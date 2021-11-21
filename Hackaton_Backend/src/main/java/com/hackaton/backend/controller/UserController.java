package com.hackaton.backend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hackaton.backend.DAO.UserServices;
import com.hackaton.backend.model.UsersDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.cliftonlabs.json_simple.JsonObject;

@CrossOrigin(origins = "http://localhost:5000")
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserServices services;
	
	
	@GetMapping("/list")
	//Listar usuarios:
	public ArrayList<UsersDTO> list(){
		return services.list();
	}
	
	@PostMapping("/search")
	//Buscar usuario en concreto:
	public UsersDTO list(@RequestParam("email") String email, @RequestParam("password") String password){
		return services.search(email, password);
	}
	
	@GetMapping("/search/{code}/{email}")
	public UsersDTO list(@PathVariable("email") String email, @PathVariable("code") Long code){
		return services.search(email, code);
	}
	
	@PostMapping("/create")
	//Crear usuario:
	public JsonObject create(@RequestBody ObjectNode object ) {
		JsonObject msj = services.create(object);
		return msj;
	}
	
	@DeleteMapping("/remove/{id}")
	//Remover usuario:
	public void remove(@PathVariable("id") long id) {
		services.remove(id);
	}
}
