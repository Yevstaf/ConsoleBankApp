package net.yevstaf.ConsoleBankApp.DataManagers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Class for storing, managing and writing data in a text document 
 * Data are stored in an {@link ArrayList} that contains {@link DataCell} objects
 * each {@link DataCell} is a line of data*/
public class Storage implements DataManager{
	private List<String> currencies;
	private List<DataCell> dataCellsList;
	public static final String SAVE_FILE = "Save.txt";
	private JSONObject rates;
	/**
	 * Class constructor that gets {@link JSONObject} rates which should contain exchange rates
	 * @param - {@link JSONObject} rates - {@link JSONObject} that stores exchange rates*/
		public Storage(JSONObject rates){
			super();
			this.rates = rates;
			currencies = getRatesFromJSONObject(rates);
			dataCellsList = new ArrayList<DataCell>();
		}
	
	/**
	 * Add new line of data
	 * @param String date - date in dd-mm-yyyy form
	 * @param String amount - number that will be processed in a double type
	 * @param String currency - exchange currency
	 * @param purchase - description of purchase*/
	public void add(String date, String ammount, String currency, String purchase){
		try{
			if(currencies.contains(currency)){
				dataCellsList.add(new DataCell(date,new Double(ammount),currency,purchase));
				
			}else{
				System.err.println("Incorrect currencie type, please write in format USD,EUR");
			}
		}catch(NumberFormatException ex){
			System.err.println("Incorrect ammount type, please write the amount");
		}
	}
	
	/**
	 *Delete all data by date
	 *@param String stringDate - date in dd-mm-yyyy form*/
	public void clear(String stringDate){
		try{
			SimpleDateFormat formatter = DataCell.getFormatter();
			Date date = formatter.parse(stringDate);
			Iterator<DataCell> iterator = dataCellsList.iterator();
			ArrayList<DataCell> remove = new ArrayList<DataCell>();
				while(iterator.hasNext()){
					DataCell cell = iterator.next();
						if(cell.getDate().equals(date)){
							remove.add(cell);
						}
				}
			dataCellsList.removeAll(remove);
		}catch(ParseException e){ 
				System.err.println("Wrong data input");
		}
	}
	
	/**
	 * Returns list of all available data, sorted by date
	 * @return String - String with date and associated data one by one*/
	public String list(){
		Collections.sort(dataCellsList,new Comparator<DataCell>() {
			@Override
			public int compare(DataCell prev, DataCell next) {			
				return prev.getDate().compareTo(next.getDate());
			}});
		
		// making String from list for list command
		SimpleDateFormat formatter = DataCell.getFormatter();
		StringBuilder sb = new StringBuilder();
		Iterator<DataCell> iterator = dataCellsList.iterator();	
		Map<Date,List<DataCell>> map = new LinkedHashMap<Date,List<DataCell>>();	
			while(iterator.hasNext()){
				DataCell cell = iterator.next();
				Date date = cell.getDate();
					if(map.containsKey(date)){
						map.get(date).add(cell); 
					}else{
						List<DataCell> list = new ArrayList<DataCell>();
						list.add(cell);
						map.put(date, list);
					}
			}
			for(Date date : map.keySet()){
				sb.append("\n" + formatter.format(date) + "\n"); // appending date first
					for(DataCell cell : map.get(date)){//then all related values
						//formatting amount before using
						 DecimalFormatSymbols symbols = new DecimalFormatSymbols();
						 symbols.setDecimalSeparator('.'); 
						 DecimalFormat df = new DecimalFormat("0.##",symbols);	
						// making String
						sb.append(cell.getPurchase() + " " + df.format(cell.getAmmount()) + " " + cell.getCurrency() + "\n");					}
			}
					
					
			
			return sb.toString();
	}
	
	/**
	 * Returns the sum of all amounts converted to the selected currency,
	 * @param String currency - currency for converting
	 * @return Double - sum of all amounts converted to the selected currency */
	public Double total(String currency){
		Double result = 0.0;
		if(currencies.contains(currency)){
			try {
				for(DataCell cell : dataCellsList){
						result += cell.getAmmount() / rates.getDouble(cell.getCurrency()); // dividing amount of money on it's rate and getting amount in euro
				}
				return result * rates.getDouble(currency); // multiplying amount of money in euro on rate required by user
			} catch (JSONException e) {
						e.printStackTrace();
					}
		}else{
			System.err.println("Incorrect currencie type, please write in format USD,EUR");
		}
		return null;
	}
	
	public void clearAll(){
		dataCellsList.clear();
	}
	
	/**
	 * Writing all {@link DataCell} objects to a text file using the form {@link JSONObject}*/
	@Override
	public void save() {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE));
				new File(SAVE_FILE).createNewFile();
					for(DataCell cell : dataCellsList){
						writer.write(cell.toString() + "\n"); 
					}
				writer.flush();
				writer.close();
			} catch (IOException e) {
					e.printStackTrace();  
			}
	}
	/**
	 * Loading all {@link DataCell} objects from a text file using the form {@link JSONObject}*/
	@Override
	public void load(){
		File file = new File(SAVE_FILE);
		if(file.exists()){
			try {
		         Scanner scanner = new Scanner(file);
		         while (scanner.hasNextLine()) {
		         String line = scanner.nextLine();
		           JSONObject object = new JSONObject(line);
		           String date = object.getString(DataCell.DATE);
		           Double ammount = new Double(object.getString(DataCell.AMMOUNT));
		           String currency = object.getString(DataCell.CURRENCY);
		           String purchase = object.getString(DataCell.PURCHASE);
		           dataCellsList.add(new DataCell(date, ammount, currency, purchase));
		         }
		         scanner.close();
		       } catch (FileNotFoundException e) {
		         e.printStackTrace();
		       }catch(JSONException ex){ 
		    	   ex.printStackTrace();
		       } 
		}
	}
	
	/**
	 * Adding keys from {@link JSONObject} to the list
	 * @param JSONObject object - object to get the keys
	 * @return ArrayList<String> - list with keys from {@link JSONObject}*/
	private ArrayList<String> getRatesFromJSONObject(JSONObject object){
		
		ArrayList<String> list = new ArrayList<String>();
			Iterator<?> iterator = object.keys();
				while(iterator.hasNext()){
						list.add((String)iterator.next());
		}
		return list;
	
	}
}
