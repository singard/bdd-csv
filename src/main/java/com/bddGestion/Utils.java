package com.bddGestion;

import java.io.File;
import java.net.URL;

public class Utils {
	
	public String getRessource() {
		 String path = "./data";
    File directory = new File(path);
    
    if (!directory.exists()) {
        directory.mkdirs();
        System.out.println("Le dossier data a été créé.");
    }
    else {
        System.out.println("Le dossier data existe déjà.");
    }
    
    return directory.getAbsolutePath();
		
	
}
	}
	
	
