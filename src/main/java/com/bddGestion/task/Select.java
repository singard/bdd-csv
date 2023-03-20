package com.bddGestion.task;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.bddGestion.Utils;
import com.bddGestion.model.ActualUser;
import com.opencsv.CSVReader;

public class Select {

	public static void selectFrom(ActualUser actualUser, String[] tokens) {

		if (tokens.length != 4 || !tokens[2].equals("FROM")) {
			System.out.println("Syntaxe incorrecte. Utilisation : SELECT * FROM <table>");
			return;
		}
		if (actualUser.getDataBaseActual()=="") {
			System.out.println("Il faut vous positionner dans une base de donner pour exécuté cette commande : MOUVE <database>");
			return;
		}

		//création du fichier
		Utils utils = new Utils();
		String fileCsv = utils.getRessource()+"/"+actualUser.getDataBaseActual()+"/"+tokens[3]+".csv";


        // Vérification de l'existence du fichier CSV
        File file = new File(fileCsv);
        if (!file.exists()) {
            System.out.println("Table " + tokens[3] + " does not exist.");
            System.exit(1);
        }

        // Lecture du contenu du fichier CSV
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
        	
            String[] header = reader.readNext();
            int numColumns = header.length;

            // Affichage de l'en-tête
            System.out.print("|");
            for (int i = 0; i < numColumns; i++) {
                System.out.printf("%-10s|", header[i]);
            }
            System.out.println();

            String separator = "-----------";
            StringBuilder repeatedSeparator = new StringBuilder();
            for (int i = 0; i < numColumns; i++) {
                repeatedSeparator.append(separator);
            }
            String separatorLine = repeatedSeparator.toString();
            System.out.println(separatorLine);
            
            // Affichage des lignes
            String[] row;
            while ((row = reader.readNext()) != null) {
                System.out.print("|");
                for (int i = 0; i < numColumns; i++) {
                    System.out.printf("%-10s|", row[i]);
                }
                System.out.println();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

	}

}
