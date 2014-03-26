package com.sands.sys.date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Officewyze
 * </p>
 * 
 * @author Stephen Ding
 * @version 1.0
 */

public class KTimestamp extends Timestamp {
	static public final String FP_STANDARD = "yyyy-MM-dd HH:mm:ss";
	static public final String FP_STANDARD_MILL = "yyyy-MM-dd HH:mm:ss:SS";
	static public final String FP_NOSEPARATOR = "yyyyMMddHHmmss";
	static public final String FP_YMD = "yyyy-MM-dd";
	static public final String FP_YMD_NOSEP = "yyyyMMdd";
	static public final String FP_NOSEPARATOR_SSSS = "yyyyMMddHHmmssSS";

	private long timeMillis;

	public KTimestamp( java.util.Date time ) {
		super( time.getTime() );
		timeMillis = time.getTime();
	}

	public KTimestamp( long time ) {
		super( time );
		timeMillis = time;
	}

	public static KTimestamp getKTimestamp( java.util.Date time, TimeZone timeZone ) throws java.text.ParseException {
		// System.out.println("--------------time="+time+"\ttimeZone="+timeZone);
		DateFormat formatter = new SimpleDateFormat( FP_STANDARD_MILL );
		formatter.setTimeZone( timeZone );
		KTimestamp ks = null;
		ks = Kalendar.StringToKTimestamp( FP_STANDARD_MILL, formatter.format( time ) );
		return ks;
	}
	
	
	public static KTimestamp toKTimestamp( String standardTimestamp ){
		try {
			return Kalendar.StringToKTimestamp( FP_STANDARD, standardTimestamp );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static KTimestamp toKTimestamp( String standardTimestamp, String tzone ) {
		SimpleDateFormat formatter = new SimpleDateFormat( FP_STANDARD );
		formatter.setTimeZone( TimeZone.getTimeZone( tzone ) );
		formatter.setLenient( false );
		try {
			KTimestamp kTimestamp = new KTimestamp( formatter.parse( standardTimestamp ) );
			return kTimestamp;
		} catch ( java.text.ParseException ex ) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public String toString( String formatPattern ){
		return Kalendar.KTimestampToString( formatPattern, this );
	}
	
	public KDate getKDate(){
		return KDate.toKDate( this.toString( KDate.FP_STANDARD ) );
	}

}