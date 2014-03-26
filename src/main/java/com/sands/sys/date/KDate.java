package com.sands.sys.date;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Officewyze</p>
 * @author Stephen Ding
 * @version 1.0
 */

public class KDate extends Date{

  private static final String NORMAL_FORMAT="yyyy-MM-dd";
  //formatPattern:
  static public final String FP_HYPHEN = "yyyy-MM-dd";
  static public final String FP_VIRGULE  = "yyyy/MM/dd";
  static public final String FP_NOSEPARATOR = "yyyyMMdd";

  static public final String FP_STANDARD = FP_HYPHEN;


  static public final String FP_TO_DB = "yyyy/MM/dd";
  static public final String FP_FROM_DB = "yyyy/mm/dd";

//  public KDate(long date){
//    super(date);
//  }
//
//  public KDate(java.util.Date date){
//    super(date.getTime());
//  }
//
//  public static KDate toKDate(String strDate) {
//    KDate kDate = null;
//    try {
//      kDate = Kalendar.StringToKDate(KDate.FP_STANDARD, strDate);
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//    }
//    return kDate;
//  }



  public static String getCurKDate(String format){
	  return getCurKDate(format,null);
  }
  
  public static String getCurKDate(String format,TimeZone timeZone){
	    String result="";
	    DateFormat formatter=new SimpleDateFormat(format);
	    try{
	      if(timeZone!=null)
	    	  formatter.setTimeZone(timeZone);
	      result = formatter.format(new java.util.Date());
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	    return result;
	  }  

   public static String getCurKDate(){
     return getCurKDate(NORMAL_FORMAT);
   }
   
   public static String getCurKDateByTimeZone(String strTimeZone){
	   if(strTimeZone==null || strTimeZone.equals(""))
		   return getCurKDate(NORMAL_FORMAT);
	   else
		   return getCurKDate(NORMAL_FORMAT,TimeZone.getTimeZone(strTimeZone));
   }

   
   public KDate(long date){
       super(date);
     }

     public KDate(java.util.Date date){
       super(date.getTime());
     }

     public String toString(){
       return  Kalendar.KDateToString(FP_STANDARD,this);
     }
     public String toString(String formatPattern){
       return Kalendar.KDateToString(formatPattern,this);
     }

     public KDate() {
       super(0);
       this.setTime(Kalendar.getCurrentDate().getTime());
     }

     public java.sql.Date toSQLDate(){
       return Kalendar.KDateToSqlDate(this);
     }

     public static String toDefaultString(String strDate){
       String ss="";
       try{
       ss=Kalendar.StringToKDate(KDate.FP_STANDARD,strDate).toString();
       }catch(Exception e){
         e.printStackTrace();
       }
       return ss;
     }

     public static KDate toKDate(String strDate) {
       KDate kDate = null;
       try {
         kDate = Kalendar.StringToKDate(KDate.FP_STANDARD, strDate);
       }
       catch (Exception e) {
         e.printStackTrace();
       }
       return kDate;
     }
     
     public static KDate toKDate(String formatPattern,String strDate) {
         KDate kDate = null;
         try {
           kDate = Kalendar.StringToKDate(formatPattern, strDate);
         }
         catch (Exception e) {
           e.printStackTrace();
         }
         return kDate;
       }
     
     /**
      * Trim hh:mi:ss for date
      * @param date
      * @return
      */
     public static KDate trim(java.util.Date date){
    	 return KDate.toKDate(new KDate(date.getTime()).toString());
     }
     
     public static KDate trim(Timestamp date){
    	 return trim(new java.util.Date(date.getTime()));
     }


     public  java.util.Date utilDate(){
    	 return new java.util.Date ( super.getTime() ) ;
     }
     
     public static void main(String[] argv){
       System.out.println("date="+KDate.toDefaultString("2004-06-03 00:00:00.000"));
       System.out.println("Amarica="+KDate.getCurKDateByTimeZone("US/Pacific"));
       System.out.println("China="+KDate.getCurKDateByTimeZone("Asia/Shanghai"));
       System.out.println("China="+KDate.getCurKDate());
       //System.out.println("date="+k.toDefaultString(str));
     }

}