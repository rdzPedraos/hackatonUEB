package com.hackaton.backend.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
	public static String upload(MultipartFile file, String path) {
		
		//Obtengo el directorio donde guardaré el archivo.
		//verifico si existe y además obtengo el número de elementos que hay
		File parent = new File(path);
		int count = 0;
		if( parent.exists() ) {
			count = parent.listFiles().length;
		}
		//Si no existe lo creo.
		else parent.mkdir();
		
		
		//Directorio:
		String realPath = path + "/" + count;
		File dest = new File(realPath);
		
		try {
			file.transferTo(dest);
			return realPath;
		}
		catch(IOException e) {
			return null;
		}
		
	}
}
