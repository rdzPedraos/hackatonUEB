package com.hackaton.backend.DAO;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.hackaton.backend.model.ImagesProductsDTO;
import com.hackaton.backend.model.ProductsDTO;
import com.hackaton.backend.model.views.ProductsView;
import com.hackaton.backend.repository.ProductsRepository;

@Service
public class ProductsServices {
	
	@Autowired
	private ProductsRepository repo;
	private ImagesProductsServices servImg;
	
	/**
	 * Devuelvo la vista de productos pero para un grupo selecto de productos.
	 * @param listProducts lista de productos
	 * @return vista de productos.
	 */
	private  ArrayList<ProductsView> list(ArrayList<ProductsDTO> listProducts){
		ArrayList<ProductsView> list = new ArrayList<ProductsView>();
		for(int i=0; i < listProducts.size(); i++) {			
			ProductsDTO product = listProducts.get(i);
			ArrayList<ImagesProductsDTO> listImg = servImg.getImgs(product.getCode());
			
			ProductsView view = new ProductsView(product, listImg);
			list.add(view);
		}
		return list;
	}
	
	/**
	 * Obtengo todos los productos disponibles.
	 * @return lista de productos con imágenes (views)
	 */
	public ArrayList<ProductsView> list() {
		return list( (ArrayList<ProductsDTO>) repo.findAll() );
	}
	
	/**
	 * Obtener lista de productos filtrada por usuario o categoria.
	 * @param id código de la tabla a la que filtramos.
	 * @param type columna de la tabla (user/category).
	 * @return lista de vista de productos con esa especificación.
	 */
	public ArrayList<ProductsView> list(Long id, String type) {
		ArrayList<ProductsDTO> list = (ArrayList<ProductsDTO>) repo.findAll();
		
		switch( type ) {
			case "user":
				list.removeIf( product -> (product.getCode_user_product() != id) );
				break;
			
			case "category":
				list.removeIf( product -> (product.getCode_category_product() != id) );
				break;
				
			default: return null;
		}
		
		return list(list);
	}
	
	
	/**
	 * Creamos un producto, para ello debemos insertar en la tabla productos
	 * y en la tabla images_products
	 * @param product contenido del producto.
	 * @param files imágenes a mostrar.
	 * @return respuesta de éxito o fracaso.
	 */
	public JsonObject create(ProductsDTO product, MultipartFile[] files) {
		//Valido los valores del producto:
		JsonObject msj = validate(product);
		
		//Si fue valido:
		if( (boolean) msj.get("success") ) {
			int i = 0;
			
			//Validamos las archivos cargados:
			while(
				i < files.length && 
				(boolean) servImg.validate(files[i]).get("success")
			) i++;
			
			//Si los valores no son iguales significa que salió del ciclo porque success es falso.
			if( i != files.length ) msj = servImg.validate(files[i]);
			
			//Si está validado la info del producto y las imágenes:
			else {
				ProductsDTO prod_save = repo.save(product);
				
				if( prod_save == null ) {
					msj.put("msj", "Uh.. No hemos podido guardar el producto.");
					msj.put("success", false);
				}
				else {
					//subo los archivos:
					for(i=0; i < files.length; i++) {
						if( !servImg.create(
								files[i], 
								prod_save.getCode(), 
								prod_save.getCode_user_product()
							)
						) {
							msj.put("success", false);
							msj.put("msj", "No se han podido cargar todos los archivos :(!");
							delete( prod_save.getCode() );
							break;
						}
					}
				}
			}
		}
		return msj;
	}
	
	
	public void delete(int id) {
		servImg.deleteByIdProduct( id );
		repo.deleteById( (long) id );
	}
	
	
	private JsonObject validate(ProductsDTO product) {
		JsonObject msj = new JsonObject();
		
		String rgx = "^([A-Za-záéíóúÁÉÍÓÚ ]{0,255}$";
		if(
			Pattern.compile(rgx).matcher(product.getTitle()).find() &&
			Pattern.compile(rgx).matcher(product.getDescription()).find() &&
			product.getPrice() >= 0 && product.getavailable() > 0
		) msj.put("success", true);
		else
		{
			msj.put("success", false);
			msj.put("msj", "Los datos suministrados no cumplen con las especificaciones dadas.");
		}
		
		return msj;
	}
}
