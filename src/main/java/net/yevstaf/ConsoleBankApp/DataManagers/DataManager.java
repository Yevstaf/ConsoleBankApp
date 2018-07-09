package net.yevstaf.ConsoleBankApp.DataManagers;
/**
* The class should be designed for an individual way of processing data*/
public interface DataManager {
	/**Save user's data in particular way*/
	public void save();
	/**Load user's data in particular way*/
	public void load();
}
