package com.bddGestion;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.bddGestion.model.ActualUser;
import com.bddGestion.task.Create;
import com.bddGestion.task.InsertInto;
import com.bddGestion.task.Mouve;
import com.bddGestion.task.Select;



public class Main {

	public static void main(String[] args) {
		Connection connection = new Connection();
		connection.readProperties();
        
        
        
		// Options de la ligne de commande
		Option userOption = Option.builder("u")
								  .longOpt("user")
								  .hasArg()
								  .desc("nom d'utilisateur")
								  .build();
		
		Option passwordOption = Option.builder("p")
									  .longOpt("password")
									  .hasArg()
									  .desc("mot de passe")
									  .build();
		
		Options options = new Options();
        options.addOption(userOption);
        options.addOption(passwordOption);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("Erreur : " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("mybdd", options);
            return;
        }
        
        String user = cmd.getOptionValue("u");
        String password = cmd.getOptionValue("p");
        
        System.out.println("test connection avec user : "+user+" / password : "+password);
        
        connection.testConnection( user, password);
        

		ActualUser actualUser = new ActualUser("", "", " >");

		Scanner scanner = new Scanner(System.in);
		String input;

		while (true) {
			System.out.print(actualUser.getLine());
			input = scanner.nextLine();

			// Séparer les commandes multiples séparées par le caractère '|'
			List<String> commandList = Arrays.asList(input.split("\\s*\\|\\s*"));
			
			for (String commandString : commandList) {
				// Parser la commande individuelle
				String[] tokens = commandString.split("\\s+");
				String command = tokens[0];

				switch (command) {

				case "CREATE":
					Create.createPath(actualUser, tokens);
					break;

				case "MOUVE":
					Mouve.mouveInDataBase(actualUser, tokens);
					break;

				case "INSERT":
					InsertInto.insertIntoValue(actualUser, tokens);
					break;
				case "SELECT":
					Select.selectFrom(actualUser, tokens);
					break;
				case "HELP":
					System.out.println("CREATE DATABASE <database>");
					System.out.println("CREATE TABLE <nomTable> (col1,col2,col3,...)");
					System.out.println("MOUVE <database>");
					System.out.println("INSERT INTO table VALUES (value1, value2, value3,...)");
					System.out.println("SELECT * FROM <table>");
					break;

				case "EXIT":
					System.out.println("Au revoir !");
					System.exit(0);

				default:
					System.out.println("Commande inconnue : \"" + command + "\".");
				}
				//SELECT * FROM table_name; 
			}
		}
	}
}



