package com.hackaton.backend.DAO;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hackaton.backend.model.InfoUserDTO;
import com.hackaton.backend.model.UsersDTO;
import com.hackaton.backend.repository.UsersRepository;

//Manipulación de json:
import com.github.cliftonlabs.json_simple.JsonObject;

@Service
public class UserServices {
	@Autowired
	private UsersRepository repo;
	
	//Servicios de la info del usuario:
	@Autowired
	private InfoUserServices servInfo;
	
	/**
	 * Función para listar todos los usuarios que no tengan el tipo disabled (Es decir, que se encuentren activos.)
	 * @return lista de usuarios.
	 */
	public ArrayList<UsersDTO> list(){
		ArrayList<UsersDTO> list = (ArrayList<UsersDTO>) repo.findAll();
		list.removeIf( user -> (user.getDisabled() == 1) );
		return list;
	}
	
	/**
	 * Busco un usuario que tenga el email y las contraseña dada.
	 * @param email < del usuario.
	 * @param password < del usuario.
	 * @return usuario si lo encontró, nulo si no.
	 */
	public UsersDTO search(String email, String password){
		//Obtengo el usuario:
		UsersDTO user = getUserByEmail(email);
		
		//Rertorna null si no encontró nada.
		//Usuario si encontró algo.
		if( user != null && user.getPassword().equals(password)) return user;
		return null;
	}
	
	public UsersDTO search(String email, Long code){
		//Obtengo el usuario:
		UsersDTO user = getUserByEmail(email);
		
		//Rertorna null si no encontró nada.
		//Usuario si encontró algo.
		if( user != null && user.getCode() == code) return user;
		return null;
	}
	
	/**
	 * Crea un usuario a la vez que crea la información de ese usuario.
	 * @param object json con la información de user e info.
	 * @return respuesta si se hizo no el registro.
	 */
	public JsonObject create(ObjectNode object) {
		JsonObject msj = new JsonObject();
		msj.put("success", false);
		
		if(
			object.get("user") != null
			&& object.get("info") != null
		) {
			//Traemos el usuario y validamos:
			UsersDTO user = jsonToUser( object.get("user") );
			msj = validateUser( user );
			
			if( (boolean) msj.get("success") ) {
				
				//Creeme un objeto con la info (tabla infoUser) y validelo:
				InfoUserDTO info = servInfo.jsonToInfo( object.get("info") );
				msj = servInfo.validateInfo( info );
				
				if( (boolean) msj.get("success") ) {
					//Si es valido el usuario y la info, entonces guarda los registros:
					UsersDTO user_save = repo.save(user);
					
					//mensaje por defecto.
					msj.put("msj", "Error al ingresar el usuario! Intentalo de nuevo.");
					//Si guardó el usuario entonces guarde la info
					if( user_save != null ) {
						//Asignamos a la id de la info el valor del usuario a guardado
						info.setCode( user_save.getCode() );
						
						if( servInfo.create(info) != null ) {
							msj.put("success", true);
							msj.put("msj", "Usuario registrado con éxito.");
						}
						//Eliminamos lo creado en caso de haber error.
						else repo.deleteById( user_save.getCode() );
					}
				}
			}			
		} else msj.put("msj", "información traida, incompleta.");
		return msj;
	}
	
	/**
	 * Remueve un usuario, deshabilitandolo de la base de datos.
	 * @param id código en la base de datos.
	 */
	public void remove(long id) {
		UsersDTO user = repo.findById(id).get();
		if(user != null ) {
			user.setDisabled(1);
			repo.save(user);			
		}
	}
	
	
	/**
	 * Obtiene el último usuario en la base de datos que contenga el email pasado.
	 * @param email < del usuario.
	 * @return usuario.
	 */
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
	
	
	public JsonObject  validateUser(UsersDTO user) {
		//Creo mi respuesta:
		JsonObject msj = new JsonObject();
		msj.put("success", false);
		
		if(
			Pattern.compile("^[A-Za-z0-9-_]+@[A-Za-z0-9-_]+(\\.[A-Za-z0-9]+)+$").matcher(user.getEmail()).find() &&
			Pattern.compile("^[A-Za-z0-9_*-]{5,}$").matcher(user.getPassword()).find()
		) {
			//Validamos si existe un usuario ya.
			UsersDTO user_exist = getUserByEmail(user.getEmail());
			
			//Si ese usuario existe y no coincide el código de su registro con el del usuario a actualizar/crear, de error.
			if( user_exist != null && user_exist.getCode() != user.getCode() ) {
				msj.put("msj", "Ya existe un usuario vinculado a ese correo.");
			}
			else msj.put("success", true);
			
		}
		else msj.put("msj", "Los campos no fueron correctamente deligenciados.");
			
		return msj;
	}
	
	
	public UsersDTO jsonToUser(JsonNode json) {
		UsersDTO user = null;
		
		if(
			json.get("email") != null
			&& json.get("password") != null
		) {
			String email = json.get("email").asText();
			String password = json.get("password").asText();
			
			user = new UsersDTO(email, password);
		}
		return user;
	}
}
