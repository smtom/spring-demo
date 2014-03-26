package com.sands.sys.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.AccessController;
import java.sql.*;
import java.util.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;






public class CommonUtil {

	
	public static boolean isDouble(Object obj){
		if(obj==null) return false;
		try{
			Double.parseDouble(obj.toString());
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean isNull(String str) {
		if (str == null) {
			return true;
		} else {
			str = str.trim();
			if (str.length() > 0) {
				if (str.equalsIgnoreCase("null")) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		}
	}
	
	public static String format(String str,String formatStyle){		
		if(!isDouble(str))
			return str;
		NumberFormat formatter=new DecimalFormat(formatStyle);
		return formatter.format(Double.parseDouble(str));		
	}
	
	public static String format(double d,String formatStyle){		
		NumberFormat formatter=new DecimalFormat(formatStyle);
		return formatter.format(d);		
	}
	
	/*
	 * This function is for replus invoice. There is no decimal for the invoice.
	 */
	public static String formatPrice(boolean isReplus,Object str){
		if(str==null || str.equals("")) return "0";
		if(!isReplus) return str+"";
		else
			return format(str+"","#################");
	}
	
	public static double formatPrice(boolean isReplus,double dstr){
		if(!isReplus) return dstr;
		else
			return Double.parseDouble(format(dstr,"#################"));
	}
	
	public static final String NUM_FMT = "##################0.00";
	
	/**
	 * formatStyle such as #########0.00 , ##0.00%
	 */
	public static String formatProcessZreo(double d,String formatStyle){
		if(Double.isInfinite(d)||Double.isNaN(d)) return "0";
		String result = format(d,formatStyle);
		return "0.00".equals(result.trim())?"0":result;
	}

	public static String formatProcessZreo(double d){
		return formatProcessZreo( d,NUM_FMT );
	}
	
	
	public static String formatNoZero(Double d){
		if(d==null||Double.isInfinite(d)||Double.isNaN(d)) return "";
		String result = format(d,NUM_FMT);
		return "0.00".equals(result.trim())?"":result;
	}
	
	public static String format(double d){
		if(Double.isInfinite(d)||Double.isNaN(d)) return "-";
		return format(d,NUM_FMT);
	}
	


	public static double toDouble(Object obj){
		try {
			return Double.parseDouble(String.valueOf(obj));
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public static int toInt(Object obj){
		try {
			return Integer.parseInt(String.valueOf(obj));
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public static String parseBoolean(boolean yesno){
		return yesno? "Y":"N";
	}
	
	public static Boolean parseBoolean(String yesno){
		return yesno!=null && yesno.equals("Y")? true:false;
	}
	
    public static String[] split(String orgStr,String findStr){
        java.util.List list = new java.util.ArrayList();
     	int index = orgStr.indexOf(findStr);
     	while(index>-1){
     		list.add(orgStr.substring(0,index));
     		orgStr = orgStr.substring(index+findStr.length(),orgStr.length());
     		index = orgStr.indexOf(findStr);
     	}
     	list.add(orgStr);
     	return (String[])list.toArray(new String[0]);
     }
    
	public static String getFileNameFromUrl(String url) {
		return url.substring(url.lastIndexOf("/") + 1, url.length());
	}


	
	public static String getLanguage(){
		String language = Locale.getDefault().getLanguage();
		String country = Locale.getDefault().getCountry();
		if(country !=null && country.length()>0)
			language = language+"_"+country;
		return language;
	}
	
	public  static String round(double v,int scale){
        if(scale<0){
            return v+"";
        }
        String format = "0";
        if(scale>0)
        	format += ".";
        for(int i=0;i<scale;i++)
        	format += "0";
        BigDecimal b = new BigDecimal(Double.toString(v));

        BigDecimal one = new BigDecimal("1");
        
        double d = b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
		return new java.text.DecimalFormat(format).format(d);    
    }
	

	
	public  static String round(String s,int scale){
        if(scale<0){
            return s;
        }
        String format = "0.";
        
        double v = Double.parseDouble(s);
        return round(v,scale);       
    }
	
	public static String getSecondsFromMicroseconds(long microseconds, int scale){
		String s = microseconds/1000.0 +"";
		return round(s,scale);
	}
	
	
	public static String getRandomStr( int length ) {
		char[] charArray = new char[length];
		int i = -1;
		while ( ++i < length ) {
			int f = (int)( Math.random() * 3 );
			if ( f == 0 )
				charArray[i] = (char)( 'A' + Math.random() * 26 );
			else if ( f == 1 )
				charArray[i] = (char)( 'a' + Math.random() * 26 );
			else
				charArray[i] = (char)( '0' + Math.random() * 10 );
		}
		return new String( charArray );
	}


	/**
	 * 
	 * @param d2a boolean digital to alphabit <p>
	 *  true: 1 to Y, 0 to N ; false: Y to 1, N to 0
	 * @param value
	 */
	public static String swithBoolean(boolean d2a, String value){
		if( StrUtils.isEmpty( value ) )
			return null;
		if( d2a ) {
			if( "YN".indexOf( value ) >= 0 )
				return value;
			return "1".equals( value.trim() ) ? "Y" : "N";
		} else {
			if( "10".indexOf( value ) >= 0 )
				return value;
			return "Y".equals( value.trim() ) ? "1" : "0";
		}
	}

	public static void main( String[] args ) {
//		System.out.println( getRandomStr( 64 ));
		System.out.println( swithBoolean(false, "N") );
	}
	
}
