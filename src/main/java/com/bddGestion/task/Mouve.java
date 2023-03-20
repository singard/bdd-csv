package com.bddGestion.task;

import java.io.File;

import com.bddGestion.Utils;
import com.bddGestion.model.ActualUser;

public class Mouve {

	public static void mouveInDataBase(ActualUser actualUser, String[] tokens) {

		if (tokens.length != 2) {
			System.out.println("Syntaxe incorrecte. Utilisation : MOUVE <database>");
			return;
		}

		String databaseName = tokens[1];
		Utils utils = new Utils();
		String databasePath = utils.getRessource()+"/"+ databaseName;

		File databaseDir = new File(databasePath);
		if (!databaseDir.exists()) {
			System.out.println("Le dossier de base de données \"" + databaseName + "\" n'existe pas.");
			return;
		}

		System.out.println("Le dossier de base de données \"" + databaseName + "\" existe.");
		actualUser.setDataBaseActual(databaseName);
		actualUser.majLine();

	}
}


