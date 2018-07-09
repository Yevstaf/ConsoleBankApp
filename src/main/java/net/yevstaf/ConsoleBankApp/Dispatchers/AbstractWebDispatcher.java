package net.yevstaf.ConsoleBankApp.Dispatchers;

import net.yevstaf.ConsoleBankApp.Parsers.WebParser;
/**
 * Getting web object using WebParser and implementing additional processing in 
 * dispatch - method*/
public abstract class AbstractWebDispatcher<I> {
		WebParser<I> parser;
		/**
		 * Class constructor for WebParser initialization
		 * @param WebParser<I> parser - object for web request handling */
		public AbstractWebDispatcher(WebParser<I> parser){
			this.parser = parser;
			
		}
		/**
		 * Realization of additional processing of web request
		 * @param String url - url for request*/
		public abstract I dispatch(String url);
		
}
