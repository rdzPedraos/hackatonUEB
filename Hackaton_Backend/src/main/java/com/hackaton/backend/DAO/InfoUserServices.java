package com.hackaton.backend.DAO;

import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.hackaton.backend.model.InfoUserDTO;
import com.hackaton.backend.repository.InfoUsersRepository;

public class InfoUserServices {
	InfoUsersRepository repo;
	
	/**
	 * Busca la información de un usuario, pasando el código de la tabla (que es el mismo de la tabla usuarios)
	 * @param id < código de la tabla.
	 * @return información del usuario.
	 */
	public InfoUserDTO getInfoByCode(Long id) {
		return repo.findById(id).get();
	}
	
	/**
	 * Creo la información del usuario.
	 * @param info
	 * @return el objeto en caso de ser creado.
	 */
	public InfoUserDTO create(InfoUserDTO info) {
		return repo.save(info);
	}
	
	/**
	 * Validamos con regex los campos.
	 * @param info objeto con la información.
	 * @return el mensaje en formato json.
	 */
	public JsonObject validateInfo(InfoUserDTO info) {
		JsonObject msj = new JsonObject();
		
		//Validamos todos los campos:
		if(
			Pattern.compile("^([A-Za-záéíóúÁÉÍÓÚ]{3,15} ?{1,2}$").matcher(info.getName()).find() &&
			Pattern.compile("^([A-Za-záéíóúÁÉÍÓÚ]{3,15} ?{1,2}$").matcher(info.getLast_name()).find() &&
			info.getAge() < 100 && info.getAge() > 0 &&
			Pattern.compile("^([A-Za-záéíóúÁÉÍÓÚ]{3,15} ?){1,45}$").matcher(info.getAddress()).find() &&
			Pattern.compile("^[0-9]{6}$").matcher(info.getZip_code()).find() &&
			( info.getMessage() == null ||
			( info.getMessage() != null && 
				Pattern.compile("^([A-Za-záéíóúÁÉÍÓÚ ]{0,255}$").matcher(info.getMessage()).find())
			)
		) msj.put("success", true);
		else{
			msj.put("success", false);
			msj.put("msj", "Los campos no fueron correctamente deligenciados.");
		}
			
		return msj;
	}
	
	public InfoUserDTO jsonToInfo(JsonNode json) {
		InfoUserDTO info = null;
		if(
			json.get("name") != null
			&& json.get("last_name") != null
			&& json.get("code_municipality") != null
			&& json.get("age") != null
			&& json.get("address") != null
			&& json.get("zip_code") != null
		) {
			info = new InfoUserDTO();
			
			info.setName( json.get("name").asText() );
			info.setLast_name( json.get("last_name").asText() );
			info.setCode_municipality( json.get("code_municipality").asInt() );
			info.setAge( json.get("age").asInt() );
			info.setAddress( json.get("address").asText() );
			info.setZip_code( json.get("zip_code").asText() );
		
			if( json.get("message") != null ) info.setMessage( json.get("message").asText() );
		}
		return info;
	}
}
