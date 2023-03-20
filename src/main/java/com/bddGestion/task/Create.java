package com.bddGestion.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.bddGestion.Utils;
import com.bddGestion.model.ActualUser;
import com.opencsv.CSVWriter;

public class Create {

	public static void createPath(ActualUser actualUser, String[] tokens) {
		switch (tokens[1]) {
		case "DATABASE":
			if (tokens.length == 3) {
				Create.createDb(tokens[2]);
			}else{
				System.out.println("Syntaxe incorrecte. Utilisation : CREATE DATABASE <database>");
				return;
			}
			break;

		case "TABLE":
			if (tokens.length == 4) {
				Create.createTable(actualUser,tokens);
			}else{
				System.out.println("Syntaxe incorrecte. Utilisation : CREATE TABLE <tableName> (col1,clo2,...)");
				return;
			}

			break;
		default :
			System.out.println("Syntaxe incorrecte.  CREATE "+tokens[1]+" existe pas.");
		}






	}

	private static void createTable(ActualUser actualUser, String[] tokens) {

		//création du fichier
		Utils utils = new Utils();
		String createFileCsv = utils.getRessource()+"/"+actualUser.getDataBaseActual()+"/"+tokens[2]+".csv";

		if (tokens.length < 4 || !tokens[1].equals("TABLE")) {
			System.out.println("Syntaxe incorrecte. Utilisation : CREATE TABLE <nomTable> <col1,col2,col3,...>");
			return;
		}
		if (actualUser.getDataBaseActual()=="") {
			System.out.println("Il faut vous positionner dans une base de donner pour exécuté cette commande : MOUVE <database>");
			return;
		}

		File file = new File(createFileCsv);
		if (file.exists()) {
			System.out.println("Le fichier csv existe déjà.");
			return;
		}

		try {
			FileWriter writer = new FileWriter(createFileCsv);
			writer.write("");
			writer.close();
			System.out.println("Le fichier CSV vide a été créé avec succès !");
		} catch (IOException e) {
			System.out.println("Une erreur s'est produite : " + e.getMessage());
			return;
		}

		//création des collones
		String input = tokens[3];
		String[] columns = input.replace("(", "").replace(")", "").split(",");
		CSVWriter csvWriter;

		try {
			csvWriter = new CSVWriter(new FileWriter(createFileCsv));  
			csvWriter.writeNext(columns);
			csvWriter.close();
			System.out.println("les colonnes on était rajoutés avec succées.");
		} catch (IOException e) {

			e.printStackTrace();
			return;
		}

	}

	public static void createDb(String databaseName) {
		Utils utils = new Utils();
		String databasePath = utils.getRessource()+"/"+databaseName;
		File databaseDir = new File(databasePath);

		if (databaseDir.exists()) {
			System.out.println("Le dossier de base de données \"" + databaseName + "\" existe déjà.");
			return;
		}

		if (!databaseDir.mkdir()) {
			System.out.println("Impossible de créer le dossier de base de données \"" + databaseName + "\".");
			return;
		}

		System.out.println("Le dossier de base de données \"" + databaseName + "\" a été créé avec succès.");

	}

}
