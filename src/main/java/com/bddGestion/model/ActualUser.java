package com.bddGestion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ActualUser {

	private String DataBaseActual;
	private String usersActual;

	private String line ;

	public void majLine() {
		String lineDatabase = "";
		if(DataBaseActual!="") {
			lineDatabase = "actual database : ".concat(DataBaseActual);
		}

		line = lineDatabase.concat(" >");
	}

}


