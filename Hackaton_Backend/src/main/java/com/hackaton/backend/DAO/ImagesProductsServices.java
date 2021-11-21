package com.hackaton.backend.DAO;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.hackaton.backend.model.ImagesProductsDTO;
import com.hackaton.backend.repository.ImagesProductsRepository;
import com.hackaton.backend.utils.FileUtils;

@Service
public class ImagesProductsServices {
	@Autowired
	ImagesProductsRepository repo;
	private static String MAIN_PATH = "C:/Users/ESTUDIANTE/Desktop/Ueb/hackaton/Hackaton_Backend/";
	private static String PATH = MAIN_PATH+"src/main/webApp";
	private static String WEB_PATH = "/assets/img/products/";
	
	
	/**
	 * Obtengo un arreglo con las imágenes de un producto.
	 * @param id id del producto.
	 * @return lista de imgs del producto.
	 */
	public ArrayList<ImagesProductsDTO> getImgs(long id) {
		//Obtengo la lista:
		ArrayList<ImagesProductsDTO> list = (ArrayList<ImagesProductsDTO>) repo.findAll();
		//Depuro la lista para tener sólo los que sean del tipo id producto.
		list.removeIf( img -> (img.getCode_product_image() != id) );
		return list;
	}
	
	
	/**
	 * Guarda los archivos y crea el registro en la base de datos.
	 * @param file archivo que guardará
	 * @param codeProduct código del producto al cual va ligado
	 * @param codeUser código del usuario al cual va ligado el producto.
	 * @return
	 */
	public boolean create(
			MultipartFile file, 
			long codeProduct, 
			long codeUser
	) {
		boolean success = false;
		
		String url = FileUtils.upload(file, PATH, WEB_PATH+codeUser+"/"+codeProduct);
		if( url != null ) {
			ImagesProductsDTO imgProd = new ImagesProductsDTO(codeProduct, url);
			if( repo.save(imgProd) != null ) {
				success = true;
			}
		}
		
		return success;
	}
	
	
	/**
	 * Elimina todas las imágenes de un producto
	 * @param id id del producto en la base de datos.
	 */
	public void deleteByIdProduct(long id) {
		//Obtengo la lista de imágenes para ese producto.
		ArrayList<ImagesProductsDTO> list = getImgs(id);
		//Lo recorro para borrarlo de la base de datos:
		for(int i=0; i < list.size(); i++) repo.deleteById( (long) list.get(i).getCode() );
	}
	
	
	/**
	 * Validamos la extensión del archivo.
	 * @param file < Archivo a validar.
	 * @return respuesta en formato json.
	 */
	public JsonObject validate(MultipartFile file){
		//por defecto falso:
		JsonObject msj = new JsonObject();
		msj.put("success", true);
		
		//obtenemos la extensión:
		String name = file.getOriginalFilename();
		int pos = name.lastIndexOf('.');
		String extension = name.substring( pos + 1 ).toLowerCase();
		
		//Validamos extensión y peso:
		if( !Pattern.compile("^((jpg)|(jpeg)|(png))$").matcher(extension).find() ) {
			msj.put("success", false);
			msj.put("msj", "Formato no valido en la imágen «"+name.substring(0, pos)+"»");
		}
		
		return msj;
	}
}
