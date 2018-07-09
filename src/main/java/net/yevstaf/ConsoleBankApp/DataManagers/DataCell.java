package net.yevstaf.ConsoleBankApp.DataManagers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * Class designed to store one line of data
 * Line Date date has type of Date for easier comparing */
public class DataCell {
	public static final String AMMOUNT = "ammount";
	public static final String CURRENCY = "currencie";
	public static final String PURCHASE = "purchase";
	public static final String DATE = "date";
	private static SimpleDateFormat formatter =  new SimpleDateFormat("yyyy-mm-dd"); 
	Date date;
	Double amount;
	String currency;
	String purchase;
	/**
	 * Class constructor to initialize 
	 * @param String date - date in (dd-mm-yyyy) form
	 * @param Float amount - number, that describes amount of money that user spent on purchase
	 * @param String currency - money currency that being used for operation
	 * @param String purchase - description of purchase*/
	public DataCell(String date, Double amount, String currency, String purchase) {
		super();
			try {
				this.date = formatter.parse(date); // String into date
			} catch (ParseException e) {
				e.printStackTrace(); 
			}
		this.amount = amount; 
		this.currency = currency;
		this.purchase = purchase;
	}
	/**
	 *@return - String that contains 
	 * Date date,
	 * Float amount,
	 * String currency,
	 * String purchase 
	 * in {@link JSONObject} form */
	@Override
	public String toString(){
		JSONObject object = new JSONObject();
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
			try {
				object.put(DATE, formatter.format(date));
				object.put(AMMOUNT, amount);
				object.put(CURRENCY, currency);
				object.put(PURCHASE, purchase);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			return object.toString();
	}

	public Date getDate() {
		return date;
	}


	public Double getAmmount() {
		return amount;
	}


	public String getCurrency() {
		return currency;
	}



	public String getPurchase() {
		return purchase.replaceAll("[^a-zA-Z0-9]", " ").trim();
	}

	public static SimpleDateFormat getFormatter() {
		return formatter;
	}

	public static void setFormatter(SimpleDateFormat formatter) {
		DataCell.formatter = formatter;
	}
	


	
	
	
}
