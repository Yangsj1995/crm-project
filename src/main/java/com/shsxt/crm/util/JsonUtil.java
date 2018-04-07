package com.shsxt.crm.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class JsonUtil {

	private  static  Gson  gson =null;
	static{
		GsonBuilder  builder= new GsonBuilder();
				builder.setPrettyPrinting();
		gson = builder.create();
	}
	
	public   static  String toJson(Object obj) {
		return gson.toJson(obj);
		
	}
	
	public static Object formJson(String str,Class classType){
		return gson.fromJson(str, classType);
		
	}
	
	
	
	
}
