package net.yevstaf.ConsoleBankApp;

import java.util.Scanner;
import org.json.JSONObject;
import com.beust.jcommander.JCommander;
import net.yevstaf.ConsoleBankApp.DataManagers.Storage;
import net.yevstaf.ConsoleBankApp.Dispatchers.JSONWebDispatcher;
import net.yevstaf.ConsoleBankApp.Parsers.JSONWebParser;
/**
 * The class designed to work with output lines(e.g. TextArea, Console ...)*/
public class ConsoleBankAppCommander implements Commander{
	private Settings settings;
	public ConsoleBankAppCommander(String url) {
		super();
		JSONObject obj = new JSONWebDispatcher(new JSONWebParser())
				.dispatch(url);
		Storage	storage = new Storage(obj);
		settings = new ConsoleCommandsSettings(storage);
	} 
	
	/**
	 * Run the command and get the answer 
	 * @param String[] command - key words and arguments
	 * @return String - response*/
		@Override
		public String execute(String line) {
			String[] command = line.split(" "); 
			JCommander.newBuilder()
			  .addObject(settings)
			  .build()
			  .parse(command);
			
			return settings.execute();
		}
		
		/**
		 * receive user's commands in cycle using keyboard scanner as input*/
		public void run(){
			try{
				while(true){
					Scanner keyboard = new Scanner(System.in);
					String line = keyboard.nextLine();
					String[] command = line.split(" ");
					System.out.println(execute(line));
					
				}
			}catch(Exception ex){
				System.err.println("Incorrect command  \"help\" for info");
				ex.printStackTrace();
				run();
			}
			
		}

}
