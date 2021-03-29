package com.sl.lolsupport.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	public String UnixToUTC(String time) {
	    long timestamp = Long.parseLong(time);
	    Date date = new java.util.Date(timestamp);
	    SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+9")); 
	    String formattedDate = sdf.format(date);
	    
	    return formattedDate;
	}
}
