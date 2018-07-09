package net.yevstaf.ConsoleBankApp.Parsers;

/**
 * Designed to convert information from the Internet to a form that we can process*/
public interface WebParser<I> {
	/**
	 * The method must read the web request (for example, the query "GET") and return it as an object
	 * @param String url - address of request */
	public I getWebObject(String url);

}
