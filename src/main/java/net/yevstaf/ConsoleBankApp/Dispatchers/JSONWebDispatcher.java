package net.yevstaf.ConsoleBankApp.Dispatchers;

import org.json.JSONException;
import org.json.JSONObject;
import net.yevstaf.ConsoleBankApp.Parsers.WebParser;
/**
 * Getting a JSONObject object using WebParser and implementing additional processing in 
 * dispatch() method*/
public class JSONWebDispatcher extends AbstractWebDispatcher<JSONObject>{
/**
 * The class constructor for WebParser <JSONObject> initializes
 *@param WebParser<JSONObject> parser - object that can make "GET" request and return JSONObject */
	public JSONWebDispatcher(WebParser<JSONObject> parser) {
		super(parser);
	}
/**
 * Getting JSONObject from WebDispatcher using fixer.io API
 * Returns a JSON object that contains only currency rates 
 * @param String url - url for "GET" request
 * @return JSONObject obj - JSONbject that contains only rates of currencies*/
	@Override
	public JSONObject dispatch(String url) {
		JSONObject obj = super.parser.getWebObject(url);
			try {
				obj = obj.getJSONObject("rates");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		
		return obj;
	}

	
	
}
