package com.bddGestion;

import java.io.File;
import java.net.URL;

public class Utils {
	
	public String getRessource() {
		 String path = "./data";
    File directory = new File(path);
    
    if (!directory.exists()) {
        directory.mkdirs();
        System.out.println("Le dossier data a �t� cr��.");
    }
    else {
        System.out.println("Le dossier data existe d�j�.");
    }
    
    return directory.getAbsolutePath();
		
	
}
	}
	
	
