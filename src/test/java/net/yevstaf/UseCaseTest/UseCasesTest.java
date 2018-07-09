package net.yevstaf.UseCaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;



import net.yevstaf.ConsoleBankApp.ConsoleBankAppCommander;


public class UseCasesTest {

	ConsoleBankAppCommander commander;
	
	@Before
	public void SetUp(){
		String url = "http://data.fixer.io/api/latest?access_key=22aa274be0bb639943c77cbef65aa8d1";
		commander = new ConsoleBankAppCommander(url);
	}
	
	@Test
	public void taskTest() {
		String line = "clearAll"; // clearing all saved data
		String expected;
		//String[] command = line.split(" "); 
		commander.execute(line);
		
		line = "add 2017-04-25 2 USD Jogurt";
		expected = "\n"+"2017-04-25"+"\n"+"Jogurt 2 USD" + "\n";
		assertEquals(expected, commander.execute(line));
		
		line = "add 2017-04-25 3 EUR \"French fries\"";
		expected = "\n"+"2017-04-25"+"\n"
				+"Jogurt 2 USD" + "\n"
				+ "French fries 3 EUR" + "\n";
		assertEquals(expected, commander.execute(line));

		line = "add 2017-04-27 4.75 EUR Beer";
		expected = "\n"+"2017-04-25"+"\n"+"Jogurt 2 USD" + "\n" 
				+ "French fries 3 EUR" + "\n" + "\n" 
				+ "2017-04-27" + "\n" 
				+ "Beer 4.75 EUR" + "\n";
		assertEquals(expected, commander.execute(line));

		line = "add 2017-04-26 2.5 PLN Sweets";
		expected = "\n"+"2017-04-25"+"\n"+"Jogurt 2 USD" + "\n"  
				+ "French fries 3 EUR" + "\n" + "\n"
				+ "2017-04-26" + "\n" 
				+ "Sweets 2.5 PLN" + "\n" + "\n"
				+ "2017-04-27" + "\n" 
				+ "Beer 4.75 EUR" + "\n";		
		assertEquals(expected, commander.execute(line));
		
		line = "list";
		expected = "\n"+"2017-04-25"+"\n"+"Jogurt 2 USD" + "\n" 
				+ "French fries 3 EUR" + "\n" + "\n"
				+ "2017-04-26" + "\n"
				+ "Sweets 2.5 PLN" + "\n" + "\n"
				+ "2017-04-27" + "\n" 
				+ "Beer 4.75 EUR" + "\n";		
		assertEquals(expected, commander.execute(line));
		
	}
	
	@Test
	public void listTest(){
		String line = "clearAll"; // clearing all saved data
		String expected;
		commander.execute(line);
		line = "add 2017-04-25 2 USD Jogurt";
		expected = "\n"+"2017-04-25"+"\n"+"Jogurt 2 USD" + "\n";
		commander.execute(line);
		line = "list";
		assertEquals(expected, commander.execute(line));
		
	}
	
	
	
	
	
		
}
