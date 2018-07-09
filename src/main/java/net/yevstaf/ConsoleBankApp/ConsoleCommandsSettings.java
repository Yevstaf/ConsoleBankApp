package net.yevstaf.ConsoleBankApp;

import java.util.ArrayList;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import net.yevstaf.ConsoleBankApp.DataManagers.Storage;
/**
 * A class intended to work with JCommander */
public class ConsoleCommandsSettings implements Settings{
	Storage storage;
	public ConsoleCommandsSettings(Storage storage) {
		super();
		this.storage = storage;
		storage.load(); //loading info from text file 
	}
	// parameters description
	@Parameter(names = { "add" }, variableArity = true, description = "Add expense entry.\n Example:  add 25-04-2018 1.20 USD Jogurt")
	  public ArrayList<String> add;

	  @Parameter(names = "list", description = " Display list of all expenses sorted by date.\n Example: list")
	  public boolean list = false;
	  
	  @Parameter(names = "close", description = "Close program.\n Example:  close")
	  public boolean close = false;
	  
	  @Parameter(names = "clear", arity = 1, description = "remove all expenses for scpecified date, "
			  + " dd-mm-yyyy - is the date for which all expenses should be removed. \n "
			  + "Example: clear 04-06-2016")
	  public String clear;
	  
	  @Parameter(names = "total", arity = 1, description = "Calculate total ammount of money spent and presented in specified currency. \n"
			  + "Example:  total PLN")
	  public String total;
	  
	  @Parameter(names = "clearAll", description = "Clear all expenses.\n Example:  clearAll")
	  public boolean clearAll = false;
	  
	  @Parameter(names = "help", description = "Show all commands")
	  public boolean help = false;
	  
	 /**
	  * The method which applies changes
	  * @return String - output*/
	  @Override
	  public String execute(){
		  StringBuilder result = new StringBuilder();
		  if(add != null){
			  StringBuilder sb = new StringBuilder();
			  if(add.size() > 3){ // 3 because all after it got to be description
				  for(int i = 3; i < add.size(); i++)
					  sb.append(add.get(i) + " ");
				  storage.add(add.get(0), add.get(1), add.get(2), sb.toString());
				  add = null;
				  storage.save(); // saving data into text file
				  result.append(storage.list());
			  }else{
				  result.append("Incorrect input \"help\" for info");
			  }
		  }
		  if(list == true){// list command
			 result.append(storage.list());
			  list = false; 
			 // storage.save();
		  }
		  if(clear != null){ //clear command
			  storage.clear(clear);
			  clear = null;
			  storage.save();
		  }
		  if(total != null){ // total command
			result.append(storage.total(total) + " " + total);
			  total = null;
			  storage.save();
		  }
		  if(close == true){
				System.out.println("End");
				System.exit(0);
			}
		  if(help == true) {
				 new JCommander(this).usage();
				 help = false;
			}
		  if(clearAll == true) {
				storage.clearAll();
				clearAll = false;
				storage.save();
			}
		  return result.toString();
		  
	  }
	  
	  
}
