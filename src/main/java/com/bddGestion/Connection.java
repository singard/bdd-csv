package com.bddGestion;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Connection {
	public static String USER = "";
	public static String PASSWORD = "";
	
	public void readProperties() {
		 // Chargement du fichier application.properties depuis le classpath
        Properties props = new Properties();
        try (InputStream is = Main.class.getResourceAsStream("/application.properties")) {
            props.load(is);
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier application.properties : " + e.getMessage());
            return;
        }

        // Récupération des propriétés user et password
        USER = props.getProperty("user");
        PASSWORD = props.getProperty("password");
        
        // Affichage des propriétés
        System.out.println("user: " + USER);
        System.out.println("password: " + PASSWORD);
		
	}
	
	public void testConnection(String user, String password) {
		
		if (USER.equals(user) && PASSWORD.equals(password)) {
			System.out.println("connection reussi.");
		}else {
			System.out.println("connection échouer.");
			System.exit(0);
		}
		
	}

}
