package com.sands.sys.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberUtil {

	public static boolean isDouble( Object obj ) {
		if ( obj == null ) return false;
		try {
			Double.parseDouble( obj.toString() );
			return true;
		} catch ( Exception e ) {
			return false;
		}
	}

	public static String format( String str, String formatStyle ) {
		if ( !isDouble( str ) ) return str;
		NumberFormat formatter = new DecimalFormat( formatStyle );
		return formatter.format( Double.parseDouble( str ) );
	}

	public static String format( double d, String formatStyle ) {
		NumberFormat formatter = new DecimalFormat( formatStyle );
		return formatter.format( d );
	}

	/*
	 * This function is for replus invoice. There is no decimal for the invoice.
	 */
	public static String formatPrice( boolean isReplus, Object str ) {
		if ( str == null || str.equals( "" ) ) return "0";
		if ( !isReplus )
			return str + "";
		else
			return format( str + "", "#################" );
	}

	public static double formatPrice( boolean isReplus, double dstr ) {
		if ( !isReplus )
			return dstr;
		else
			return Double.parseDouble( format( dstr, "#################" ) );
	}

	public static final String NUM_FMT = "##################0.00";

	/**
	 * formatStyle such as #########0.00 , ##0.00%
	 */
	public static String formatProcessZreo( double d, String formatStyle ) {
		if ( Double.isInfinite( d ) || Double.isNaN( d ) ) return "0";
		String result = format( d, formatStyle );
		return "0.00".equals( result.trim() ) ? "0" : result;
	}

	public static String formatProcessZreo( double d ) {
		return formatProcessZreo( d, NUM_FMT );
	}

	public static String formatNoZero( Double d ) {
		if ( d == null || Double.isInfinite( d ) || Double.isNaN( d ) ) return "";
		String result = format( d, NUM_FMT );
		return "0.00".equals( result.trim() ) ? "" : result;
	}

	public static String format( double d ) {
		if ( Double.isInfinite( d ) || Double.isNaN( d ) ) return "-";
		return format( d, NUM_FMT );
	}

	
	
	public static double toDouble( Object obj ) {
		return toDouble(obj, 0D);
	}
	
	public static double toDouble( Object obj, double dflt ) {
		if ( obj == null ) {
			return dflt;
		}
		try {
			return Double.parseDouble( String.valueOf( obj ).trim() );
		} catch ( NumberFormatException e ) {
			return dflt;
		}
	}
	public static long toLong( String str ) {
		return toLong( str, 0L );
	}

	/**
	 * <p>
	 * If the obj is <code>null</code>, the default value is returned.
	 * </p>
	 * 
	 * <pre>
	 *   NumberUtil.toLong(null, 1L) = 1L
	 *   NumberUtil.toLong("", 1L)   = 1L
	 *   NumberUtil.toLong("1", 0L)  = 1L
	 * </pre>
	 */
	public static long toLong( Object obj, long dflt ) {
		if ( obj == null ) {
			return dflt;
		}
		try {
			return Long.parseLong( String.valueOf( obj ) );
		} catch ( NumberFormatException nfe ) {
			return dflt;
		}
	}
	
	public static int toInt( Object obj ) {
		return toInt( obj, 0 );
	}

	public static int toInt( Object obj, int dflt ) {
		if ( obj == null ) {
			return dflt;
		}
		try {
			return Integer.parseInt( String.valueOf( obj ) );
		} catch ( NumberFormatException e ) {
			return dflt;
		}
	}
	
	
	public static String round( double v, int scale ) {
		if ( scale < 0 ) {
			return v + "";
		}
		String format = "0";
		if ( scale > 0 ) format += ".";
		for (int i = 0; i < scale; i++)
			format += "0";
		BigDecimal b = new BigDecimal( Double.toString( v ) );

		BigDecimal one = new BigDecimal( "1" );

		double d = b.divide( one, scale, BigDecimal.ROUND_HALF_UP ).doubleValue();
		return new java.text.DecimalFormat( format ).format( d );
	}

	public static String round( String s, int scale ) {
		if ( scale < 0 ) {
			return s;
		}
		double v = Double.parseDouble( s );
		return round( v, scale );
	}
	
	public static double round(int scale ,double d ){
		return Double.valueOf( round(d,scale));
	}
	
	public static Double roundPrice_X0_X5( double d ) {
		return Double.parseDouble( round( Double.parseDouble(round(d*2,0))/2 , 2) );
	}
	
	public static boolean zeroIn( double[] d ) {
		if ( d==null) 
			return true;
		for (double e : d) {
			if ( Math.abs( e ) < 0.001 )
				return true;
		}
		return false;
	}
	
	public static boolean equalOf(Double a, Double b, int scale){
		if(a == null && b == null) return true;
		if(a == null || b == null) return false;
		return equal(a*1d, b*1d, scale);
	}
	
	/**
	 * Only keep two decimal point
	 */
	public static boolean equal(Double a, Double b){
		return equalOf(a,b,2);
	}
	
	public static boolean equal(Long a, Long b){
		if(a == null && b == null) return true;
		if(a == null || b == null) return false;
		return a.longValue() == b.longValue();
	}
	
	public static boolean equal(Integer a, Integer b){
		if(a == null || b == null)
			return false;
		return equal(a, b, 2);
	}
	
	public static boolean equal(double a, double b, int scale){
		return Math.abs( a - b ) < 1/Math.pow( 10, scale );
	}
	
	/**
	 * <p>
	 * If <code>null</code>, the default value is returned.
	 * </p>
	 * 
	 * <pre>
	 *   NumberUtil.nvl(null, 1L) = 1L
	 *   NumberUtil.nvl(null, 0D) = 0D
	 *   NumberUtil.nvl(null, 0) = 0
	 *   NumberUtil.nvl(1, 0L)  = 1
	 * </pre>
	 */
	public static <T> T nvl( T t, Object defaul ) {
		return  t == null ? (T)defaul : t;
	}
	
	public static double null2Zero( Double price ) {
		return price == null ? 0 : price;
	}
	
	public static boolean nullOrZero( double... doubles  ){
		if( doubles == null || doubles.length < 1 )
			return true;
		for (Double d : doubles) {
			if( Math.abs( d ) < 0.001 )
				return true;
		}
		return false;
	}
	
	public static boolean nullOZero( Double... doubles  ){
		if( doubles == null || doubles.length < 1 )
			return true;
		for (Double d : doubles) {
			if( d== null ||  Math.abs( d ) < 0.001 )
				return true;
		}
		return false;
	}
	
	public static boolean null0(Double d, Double precise){
		if( d == null ) return true;
		return Math.abs( d ) < (precise == null ? 0.001 : precise);
	}
	
	public static String toStr(double...ds){
		if(ds == null ) return null;
		if(ds.length <1 ) return "";
		StringBuilder sb = new StringBuilder();
		for (double d : ds) {
			sb.append( d ).append( "," );
		}
		sb.deleteCharAt( sb.length()-1 );
		return sb.toString();
	}

	public static boolean isDigits( String str) {
		return str.matches( "^([0-9]\\d*)"  );
	}
	
	
	public static void main( String[] args ) {
//		System.out.println( zeroIn( new double[] {0.1, 001} ) );
//		System.out.println( equal( 2.399999999, 2.40000011 ) );
//		System.out.println( equal( null, 0.05 ) );
//		int a = nvl( null,0 );
//		System.out.println( a );
//		System.out.println( toStr( null ));
//		System.out.println( nullOZero(null, 5d));
//		System.out.print( round( 6.325, 2 ) );
//		System.out.println( isDigits("0d12") );
		
//		System.out.println( roundPrice_X0_X5( 1.175 ));
//		System.out.println( equalOf( 0.045, 0.040, 4 ) );
		
		System.out.println( round(2, 2.624900) );
		System.out.println( round(2, 2.625000) );
		System.out.println( round(2, 2.625100) );
		System.out.println( round(2, 2.634900) );
		System.out.println( round(2, 2.635000) );
		System.out.println( round(2, 2.635100) );
		System.out.println( round(2, 2.534900) );
		System.out.println( round(2, 2.535000) );
		System.out.println( round(2, 2.535100) );
		System.out.println( round(2, 2.544900) );
		System.out.println( round(2, 2.545000) );
		System.out.println( round(2, 2.545100) );


	}





}
