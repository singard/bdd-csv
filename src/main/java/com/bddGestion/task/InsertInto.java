package com.bddGestion.task;

import java.io.FileWriter;
import java.io.IOException;

import com.bddGestion.Utils;
import com.bddGestion.model.ActualUser;
import com.opencsv.CSVWriter;

public class InsertInto {




	public static void insertIntoValue (ActualUser actualUser, String[] tokens) {
		if (tokens.length != 5 || !tokens[1].equals("INTO")) {
			System.out.println("Syntaxe incorrecte. Utilisation : INSERT INTO table VALUES (value1, value2, value3)");
			return;
		} 

		Utils utils = new Utils();
		String fileCsv = utils.getRessource()+"/"+actualUser.getDataBaseActual()+"/"+tokens[2]+".csv";

		String input = tokens[4];
		String[] columns = input.replace("(", "").replace(")", "").split(",");
		CSVWriter csvWriter;

		try {
			csvWriter = new CSVWriter(new FileWriter(fileCsv,true));  
			csvWriter.writeNext(columns);
			csvWriter.close();
			System.out.println("les données on était rajoutés avec succées.");
		} catch (IOException e) {

			e.printStackTrace();
			return;
		}

	}

}
