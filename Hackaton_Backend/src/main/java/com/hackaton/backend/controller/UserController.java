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
	
	@GetMapping("/search/{email}/{password}")
	//Buscar usuario en concreto:
	public UsersDTO list(@PathVariable("email") String email, @PathVariable("password") String password){
		return services.search(email, password);
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
