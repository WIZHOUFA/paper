package com.fnst.paper.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
    public static String[] delStrings(String[] selectStr, String[] deleteStr) {
    	for (int i = 0; i < deleteStr.length; i ++) {
    		String str = deleteStr[i];
    		for (int j = 0; j < selectStr.length; j ++) {
    			if (selectStr[j].equals(str)) {
    				for(int k = j; k < selectStr.length - 1; k ++) {
    					selectStr[k] = selectStr[k + 1];
    				}
    				j --;
    			}
    		}
    	}
    	return selectStr;
    }
    
    public static Date parseStr(String regix, String dateStr){
    
    	SimpleDateFormat formart = new SimpleDateFormat(regix);
    	Date date = null;
    	try {
			date = formart.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return date;
    }
    
    public static String formartDate(String regix, Date date){
        
    	SimpleDateFormat formart = new SimpleDateFormat(regix);
    	String dateStr = null;
    	dateStr = formart.format(date);
    	return dateStr;
    }
}
