package net.yevstaf.ConsoleBankApp.Parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * JSONWebParser uses {@link JSONObject} as a type to write information about the URL*/

public class JSONWebParser implements WebParser<JSONObject>{
/**
 * Setting the "GET" request and writing it to JSONObject
 * @param String url - should be Fixer.io API*/
	
	@Override
	public JSONObject getWebObject(String url) {
		StringBuffer response = new StringBuffer();	
		// "GET" request
		try { 	
			URL obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
	
				connection.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
	
				while ((inputLine = in.readLine()) != null) {
				    response.append(inputLine); 
				}
			
				in.close();
			return new JSONObject(response.toString());
			} catch (JSONException e) {
				System.err.println("No interner connection, please try again later");
			//e.printStackTrace();
			} catch (IOException e) {
				System.err.println("No interner connection, please try again later");
			//e.printStackTrace();
		}
		return null;
	
	}

}
