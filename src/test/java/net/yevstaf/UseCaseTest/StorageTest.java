package net.yevstaf.UseCaseTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import net.yevstaf.ConsoleBankApp.DataManagers.Storage;
import net.yevstaf.ConsoleBankApp.Dispatchers.JSONWebDispatcher;
import net.yevstaf.ConsoleBankApp.Parsers.JSONWebParser;

public class StorageTest {
	Storage	storage;
	@Before
	public void setUp(){
		String url = "http://data.fixer.io/api/latest?access_key=22aa274be0bb639943c77cbef65aa8d1";
		JSONObject obj = new JSONWebDispatcher(new JSONWebParser())
				.dispatch(url);
		storage = new Storage(obj);
	}
	
	@Test
	public void addTest() {
		String expected =  "\n"+"2017-04-25"+"\n"+"Jogurt 2 USD" + "\n";
		storage.clearAll();
		storage.add("2017-04-25", "2","USD","Jogurt");
		assertEquals(expected,storage.list()); 
		
		storage.add("2017-04-25", "2","UNNOWN CURRENCY","Jogurt");
		assertEquals(expected,storage.list());
		
		storage.add("2017-04-25", "WRONG NUMBER INPUT","USD","Jogurt");
		assertEquals(expected,storage.list());
		
		expected =  "\n"+"2017-04-25"+"\n"+"Jogurt 2 USD" + "\n"
					+ "Nuts 0.5 EUR" + "\n";
		storage.add("2017-04-25", "0.50","EUR","Nuts");
		assertEquals(expected,storage.list());
		
	}
	
	@Test 
	public void totalTest() {
		Double expected = 5.0;
		storage.clearAll();
		storage.add("2017-04-25", "5","USD","Jogurt"); 
		assertEquals(expected, storage.total("USD"));
		
		expected = null;
		assertEquals(expected, storage.total("unnown currency"));
		
	}
	
	@Test 
	public void clearTest(){
		String expected =  "\n"+"2017-04-25"+"\n"+"Jogurt 2 USD" + "\n";
		storage.clearAll();
		storage.add("2017-04-25", "2","USD","Jogurt");
		storage.add("2018-04-25", "12","USD","Earphones");
		
		storage.clear("2018-04-25"); 
		assertEquals(expected, storage.list());
		
		storage.clear("Unnown date type");
		assertEquals(expected, storage.list());
	}
	
	@Test
	public void saveLoadTest() { 
		String expected =  "\n"+"2017-04-25"+"\n"+"Bananas 2 USD" + "\n";
		storage.clearAll();
		storage.add("2017-04-25", "2","USD","Bananas ");
		storage.save();
		
		String url = "http://data.fixer.io/api/latest?access_key=22aa274be0bb639943c77cbef65aa8d1";
		JSONObject obj = new JSONWebDispatcher(new JSONWebParser())
				.dispatch(url);
		storage = new Storage(obj);
		
		storage.load();
		
		assertEquals(expected, storage.list());
	}
	
	@Test
	public void totalCommandTest() {
		storage.clearAll();
		storage.add("2017-04-25", "2","USD","Bananas ");
		storage.add("2017-04-25", "2","USD","Bananas ");
		storage.add("2017-04-25", "2","USD","Bananas ");
		Double usd = storage.total("USD");
		Double rub = storage.total("RUB");
		assertTrue(usd < rub); 
		
	}
	
	
	
	
	
	
}
