package com.example.utility;

import com.google.gson.Gson;

public class JsonUtility<T> {
	
	private static Gson gson;
	
	private final Class<T> type;
	
	public JsonUtility(Class<T> type) {
		gson = new Gson();
		this.type = type;
	}
	
	public T convertToModel(String jsonPayload) {
		if(jsonPayload == null || jsonPayload.isEmpty()) 
			throw new IllegalArgumentException("JSON cannot be null");
		T t = gson.fromJson(jsonPayload, type );
		return t;
	}
	
	String convertToPayload(T model){
		//if(model == null) throw new NullPointerException("Model cannot be null");
		String jsonPayload = gson.toJson(model);
		return jsonPayload;
	}

}
