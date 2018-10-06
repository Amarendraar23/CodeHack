package com.nokia.uni.sri;

import java.util.HashMap;

public class DataStore {

	private static HashMap<String,Time> dataStore = null;
	public DataStore() {
		
	}
	public static HashMap<String,Time> getDataStore(){
		if(dataStore==null) {
			dataStore = new  HashMap<>();
			dataStore.put("SchoolExit", new Time(16,7));
			dataStore.put("ClassRoom", new Time(8,16));
			dataStore.put("Bus", new Time(7,8));	
		}
		return dataStore;
	}
}
