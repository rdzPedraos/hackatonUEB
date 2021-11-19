package com.hackaton.backend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hackaton.backend.model.UsersDTO;
import com.hackaton.backend.repository.UsersRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UsersRepository repository;
	
	@GetMapping("/list")
	public ArrayList<UsersDTO> list(){
		ArrayList<UsersDTO> list = (ArrayList<UsersDTO>) repository.findAll();
		list.removeIf( user -> (user.getDisabled() == 1) );
		return list;
	}
	
	@GetMapping("/search/{email}/{password}")
	public UsersDTO list(@PathVariable("email") String email, @PathVariable("password") String password){
		//Obtengo el usuario:
		UsersDTO user = getUserByEmail(email);
		
		//Rertorna null si no encontró nada.
		//Usuario si encontró algo.
		if( user != null && user.getPassword().equals(password)) return user;
		return null;
	}
	
	@PostMapping("/create")
	public UsersDTO update(@RequestBody UsersDTO user) {
		if( getUserByEmail(user.getEmail()) == null ) 
		{
			user.setCode_role_user(2);
			UsersDTO user_create = repository.save(user);
			return user_create;
		}
		return null;
	}
	
	@DeleteMapping("/remove/{id}")
	public void remove(@PathVariable("id") long id) {
		UsersDTO user = repository.findById(id).get();
		user.setDisabled(1);
		update(user);
	}
	
	private UsersDTO getUserByEmail(String email) {
		//Obtengo mi lista:
		ArrayList<UsersDTO> list = list();
		//usuario final:
		UsersDTO user = null;
		
		//Busco el primer usuario que encuentre con las caracteristicas, de la última pos a la primera.
		int i = list.size()-1;
		while(i >= 0 && !list.get(i).getEmail().equals(email)) i--;
		
		//En caso de que exista registro retorna el usuario, de lo contrario retorna null.
		if(i>=0) user = list.get(i);
		return user;
	}
}
