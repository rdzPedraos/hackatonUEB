package com.hackaton.backend.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
	public static String upload(MultipartFile file, String path) {
		//Obtengo el directorio donde guardaré el archivo.
		//verifico si existe y además obtengo el número de elementos que hay
		System.out.println("Obtengo la ruta relativa: "+new File(".").getAbsolutePath());
		
		File parent = new File(path);
		
		int count = 0;
		if( parent.exists() ) {
			count = parent.listFiles().length;
			System.out.println("________________________");
			System.out.println("Contador: "+count);
			System.out.println("________________________");
		}
		//Si no existe lo creo.
		else System.out.println("Creación: "+parent.mkdirs());
		
		
		//Directorio:
		String name = file.getOriginalFilename();
		int pos = name.lastIndexOf('.');
		String extension = name.substring(pos + 1).toLowerCase();
		
		String realPath = path + "/" + count + "."+extension;
		File dest = new File(realPath);
		
		try {
			file.transferTo(dest);
			return realPath;
		}
		catch(IOException e) {
			System.out.println("________________________");
			System.out.println("Exception: "+e);
			System.out.println("________________________");
			return null;
		}
		
	}
}
