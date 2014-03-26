package com.sands.sys.date;
import java.sql.Timestamp;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Officewyze</p>
 * @author Stephen Ding
 * @version 1.0
 */

//public class KDateTime extends Timestamp{
//
//  //formatPattern:
//  static public final String FP_HYPHEN = "yyyy-MM-dd HH:mm:ss";
//  static public final String FP_VIRGULE = "yyyy/MM/dd/HH/mm/ss";
//  static public final String FP_NOSEPARATOR = "yyyyMMddHHmmss";
//
//  static public final String FP_TO_DB = "yyyy/MM/dd HH:mm:ss";
//  static public final String FP_FROM_DB = "yyyy/mm/dd hh24:mi:ss";
//
//
//  static public final String FP_STANDARD = FP_HYPHEN;
//
//  public KDateTime(java.util.Date datetime){
//    super(datetime.getTime());
//  }
//
//  public KDateTime(long datetime){
//    super(datetime);
//  }
//
//  public String toString(){
//
//    return  Kalendar.KDateTimeToString(FP_STANDARD,this);
//  }
  public class KDateTime extends Timestamp {
      //formatPattern:
      static public final String FP_HYPHEN = "yyyy-MM-dd HH:mm:ss";
      static public final String FP_VIRGULE = "yyyy/MM/dd/HH/mm/ss";
      static public final String FP_NOSEPARATOR = "yyyyMMddHHmmss";

      static public final String FP_TO_DB = "yyyy/MM/dd HH:mm:ss";
      static public final String FP_FROM_DB = "yyyy/mm/dd hh24:mi:ss";


      static public final String FP_STANDARD = FP_HYPHEN;

      public KDateTime(java.util.Date datetime){
        super(datetime.getTime());
      }

      public KDateTime(long datetime){
        super(datetime);
      }

      public String toString(){
        return  Kalendar.KDateTimeToString(FP_STANDARD,this);
      }
      public String toString(String formatPattern){
        return Kalendar.KDateTimeToString(formatPattern,this);
      }

      public KDateTime(){
        super(0);
        this.setTime(Kalendar.getCurrentDateTime().getTime());
      }


      ////////////////////////////////////////////////////////////////////
      /**
       * 暂时保留，仍然需要转换。
       */
      public java.sql.Timestamp toSQLTimestamp(){
        return Kalendar.KDateTimeToSqlTimestamp(this);
      }

      public static KDateTime toKDateTime(String strDate) {
      KDateTime kDateTime = null;
      try {
        kDateTime = Kalendar.StringToKDateTime(KDateTime.FP_STANDARD, strDate);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      return kDateTime;
    }
      
      


      public static String toDefaultString(String strDate) {
        String ss = "";
        try {
          ss = Kalendar.StringToKDateTime(KDateTime.FP_STANDARD, strDate).toString();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
        return ss;
      }

      public static void main(String[] argv) {
        KDateTime time=new KDateTime();
        System.out.println("date=" +
                           KDateTime.toDefaultString("2004-06-03 12:01:04.000"));
        System.out.println("cur time="+time.toString());
        //System.out.println("date="+k.toDefaultString(str));
      }

  
}