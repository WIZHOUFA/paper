package com.fnst.paper.util;

public class DataBaseUtil {
	
	private static DataBaseUtil dataBaseUtil = new DataBaseUtil();

	public DataBaseUtil() {
		
	}
	
	public static DataBaseUtil getInstance() {
		return dataBaseUtil;
	}
}
