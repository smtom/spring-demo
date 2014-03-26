package com.sands.sys.date;

import java.sql.Time;

/**
 * <p>Title: </p>
 * <p>Description: 日期处理类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * @author Bach Ding
 * @version 1.0
 */


public class KTime extends Time{
  //formatPattern:
  static public final String FP_COLON = "HH:mm:ss";
  static public final String FP_HYPHEN = "yyyy-MM-dd";
  static public final String FP_VIRGULE = "HH/mm/ss";
  static public final String FP_NOSEPARATOR = "HHmmss";

  static public final String FP_STANDARD = FP_COLON;

  /**
   * 存取数据库时，只有时分秒，没有日期
   */
  static public final String FP_TO_DB = "HH:mm:ss";
  static public final String FP_FROM_DB = "hh24:mi:ss";

  public KTime(java.util.Date time){
    super(time.getTime());
  }
  public KTime(long time){
    super(time);
  }
  public String toString(){
    return  Kalendar.KTimeToString(FP_STANDARD,this);
  }
  public String toString(String formatPattern){
    return Kalendar.KTimeToString(formatPattern,this);
  }

  public KTime(){
    super(0);
    this.setTime(Kalendar.getCurrentTime().getTime());
  }
  ///////////////////////////////////////////////////////////////////
  /**
   * @deprecated :delete!
   */
  public java.sql.Date toSQLDate(){
    return new java.sql.Date((new java.util.Date()).getTime());
  }

  /**
   * 暂时保留，仍然需要转换。
   */
  public java.sql.Time toSQLTime(){
    return this;
  }
  
  
  public static void main(String[] argv){
	  KTime time=new KTime(KDateTime.toKDateTime("2005-01-02 14:01:33"));
	  System.out.println(time.toString());
	  System.out.println( time.toString("hh:mm a"));
	  
  }


}