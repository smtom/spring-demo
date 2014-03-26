package com.sands.sys.date;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.sql.Date;
import java.text.*;


/**
 * <p>Title: </p>
 * <p>Description: ���ڴ�����</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * @author Bach Ding
 * @version 1.0
 * ���ܼ���

����
    KDate��KTime��KDateTime��KTimestamp����������ҵ���߼���洢����ʱ�䣬���У�?
      KDate       �洢������
      KTime       �洢ʱ����
      KDateTime   �洢������ʱ����
      KTimestamp  �洢ʱ��c���ȷ��΢��?

ת���ͷ�����
    �ṩKDate

�����ͷ���:
    �õ���ĳ�쿪ʼn���ǰ�������ڣ������գ�?

    �Ƚ�}��ʱ�� ��λ���죬��֮ǰ��֮��ͬһ�졣

    �ж�һ��ʱ���Ƿ���һ��ʱ���� ��λ�У���

    ����}��ʱ���ڼ������?

 */


  /**
   * ��formatPattern��ĸ��?
        Symbol   Meaning                 Presentation        Example
        ------   -------                 ------------        -------
        G        era designator          (Text)              AD
        y        year                    (Number)            1996
        M        month in year           (Text & Number)     July & 07
        d        day in month            (Number)            10
        h        hour in am/pm (1~12)    (Number)            12
        H        hour in day (0~23)      (Number)            0
        m        minute in hour          (Number)            30
        s        second in minute        (Number)            55
        S        millisecond             (Number)            978
        E        day in week             (Text)              Tuesday
        D        day in year             (Number)            189
        F        day of week in month    (Number)            2 (2nd Wed in July)
        w        week in year            (Number)            27
        W        week in month           (Number)            2
        a        am/pm marker            (Text)              PM
        k        hour in day (1~24)      (Number)            24
        K        hour in am/pm (0~11)    (Number)            0
        z        time zone               (Text)              Pacific Standard Time
        '        escape for text         (Delimiter)
        ''       single quote            (Literal)           '
     ���� ��formatPattern = "yyyy.MM.dd G 'at' hh:mm:ss z"
            dateString = "1996.07.10 AD at 15:08:56 PDT"
            �Ϳ��Խ����߱�ʾ��ʱ��ת����ʱ�����?
     ���õ�formatPattern���ø���ʱ�������ೣ4��ʾ��
   *
   */


public class Kalendar {

  public final static String MODULE_NAME = "Kalendar";

/**
 * ��String ת����KDate��KTime��KDateTime��KTimestamp����
 */
  static public KDate StringToKDate(String formatPattern,String dateString)
          throws java.text.ParseException{
    SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
    formatter.setLenient(false);
    try {
      KDate kDate = new KDate(formatter.parse(dateString));
      return kDate;
    }catch (java.text.ParseException ex) {
//      throw new ApplException("ʱ��ת�����������ʱ���ʽ���ԡ�","ʱ��ת�����������ʱ���ʽ���ԡ�");
      throw ex;
    }

  }
  static public KTime StringToKTime(String formatPattern,String dateString)
          throws java.text.ParseException{
    SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
    formatter.setLenient(false);
    try{
      KTime kTime = new KTime(formatter.parse(dateString));
      return kTime;
    }catch (java.text.ParseException ex) {
//      throw new ApplException("ʱ��ת�����������ʱ���ʽ���ԡ�","ʱ��ת�����������ʱ���ʽ���ԡ�");
      throw ex;
    }
  }
  static public KDateTime StringToKDateTime(String formatPattern,String dateString)
          throws java.text.ParseException{
    SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
    formatter.setLenient(false);
    try{
      KDateTime kDateTime = new KDateTime(formatter.parse(dateString));
      return kDateTime;
    }catch (java.text.ParseException ex) {
//      throw new ApplException("ʱ��ת�����������ʱ���ʽ���ԡ�","ʱ��ת�����������ʱ���ʽ���ԡ�");
      throw ex;
    }
  }
  static public KTimestamp StringToKTimestamp(String formatPattern,String dateString)
          throws java.text.ParseException{
    SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
    formatter.setLenient(false);
    try{
      KTimestamp kTimestamp = new KTimestamp(formatter.parse(dateString));
      return kTimestamp;
    }catch (java.text.ParseException ex) {
//      throw new ApplException("ʱ��ת�����������ʱ���ʽ���ԡ�","ʱ��ת�����������ʱ���ʽ���ԡ�");
      throw ex;
    }
  }

  
/**
 * ��KDate��KTime��KDateTime��KTimestampת����String
 */
  static private String toString(String formatPattern,java.util.Date dateObject){
    SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);
    String dateString = dateFormat.format(dateObject);
    return dateString;
  }

  static public String KDateToString(String formatPattern,KDate kDate){
    return toString(formatPattern,kDate);
  }

  static public String KTimeToString(String formatPattern,KTime kTime){
    return toString(formatPattern,kTime);
  }

  static public String KDateTimeToString(String formatPattern,KDateTime kDateTime){
    return toString(formatPattern,kDateTime);
  }

  static public String KTimestampToString(String formatPattern,KTimestamp dateObject){
    return toString(formatPattern,dateObject);
  }


/**
 * ��java.sql.dateת����KDate��KTime��KDateTime��KTimestamp
 */
  static public KDate SqlDateToKDate(java.sql.Date date){
    KDate kDate = new KDate(date.getTime());
    return kDate;
  }

  static public KTime SqlTimeToKTime(java.sql.Time time){
    KTime kTime = new KTime(time.getTime());
    return kTime;
  }

  static public KDateTime SqlTimestampToKDateTime(java.sql.Timestamp timestamp){
    KDateTime kDateTime = new KDateTime(timestamp.getTime());
    return kDateTime;
  }

  static public KTimestamp SqlTimestampToKTimestamp(java.sql.Timestamp timestamp){
    if (timestamp == null)
      return null;
    KTimestamp kTimestamp = new KTimestamp(timestamp.getTime());
    return kTimestamp;
  }

/**
 * ��KDate��KTime��KDateTime��KTimestampת����java.sql.Date
 */
  static private java.sql.Date toSqlDate(java.util.Date dateObject){
    java.sql.Date date = new java.sql.Date(dateObject.getTime());
    return date;
  }

  /**
   * д����ݿ��ʱ����Ҫת��һ��
   */
  static public java.sql.Date KDateToSqlDate(KDate kDate){
    return toSqlDate(kDate);
  }

  /**
   * deprecated��
   * �����ԣ�����ֱ�Ӵ���stm.setTime()����˻�����Ҫ������?
   * д����ݿ��ʱ����Ҫת��һ��
   */
  static public java.sql.Time KTimeToSqlTime(KTime kTime){
    return new java.sql.Time(kTime.getTime());
  }


  /**
   * д����ݿ��ʱ����Ҫת��һ��
   */
  static public java.sql.Timestamp KDateTimeToSqlTimestamp(KDateTime kDateTime){
    return new java.sql.Timestamp(kDateTime.getTime());
  }

  /**
   * д����ݿ��ʱ����Ҫת��һ��
   */
  static public java.sql.Timestamp KTimestampToSqlTimestamp(KTimestamp kTimestamp){
    return new java.sql.Timestamp(kTimestamp.getTime());
  }


/**
 * KDate��KTime��KDateTime��KTimestamp֮���һЩת��?
 */

  static public KDate KDateTimeToKDate(KDateTime kDateTime){
    Calendar calendarTimestamp = new GregorianCalendar();
    calendarTimestamp.setTime(kDateTime);
    Calendar calendar = new GregorianCalendar(calendarTimestamp.get(Calendar.YEAR),
                                              calendarTimestamp.get(Calendar.MONTH),
                                              calendarTimestamp.get(Calendar.DATE),
                                              0,
                                              0,
                                              0);
    KDate kDate = new KDate(calendar.getTime());
    return kDate;
  }

  static public KDate KDateTimeToKDate(String strKDateTime){
  return KDateTimeToKDate(KDateTime.toKDateTime(strKDateTime));
}


  static public KDate KTimestampToKDate(KTimestamp kTimestamp){
//    Calendar calendarTimestamp = new GregorianCalendar();
//    calendarTimestamp.setTime(kTimestamp);
//    Calendar calendar = new GregorianCalendar(calendarTimestamp.get(Calendar.YEAR),
//                                              calendarTimestamp.get(Calendar.MONTH),
//                                              calendarTimestamp.get(Calendar.DATE),
//                                              0,
//                                              0,
//                                              0);
//    KDate kDate = new KDate(calendar.getTime());
//    return kDate;
    try {
      String temp = Kalendar.KTimestampToString(KDate.FP_STANDARD,kTimestamp);
      KDate kDate = Kalendar.StringToKDate(KDate.FP_STANDARD,temp);
      return kDate;
    } catch (ParseException ex) {
      return null;
    }
  }

  static public KDateTime KTimestampToKDateTime(KTimestamp kTimestamp){

//    Calendar calendarTimestamp = new GregorianCalendar();
//    calendarTimestamp.setTime(kTimestamp);
//    Calendar calendar = new GregorianCalendar(calendarTimestamp.get(Calendar.YEAR),
//                                              calendarTimestamp.get(Calendar.MONTH),
//                                              calendarTimestamp.get(Calendar.DATE),
//                                              calendarTimestamp.get(Calendar.HOUR),
//                                              calendarTimestamp.get(Calendar.MINUTE),
//                                              calendarTimestamp.get(Calendar.SECOND));
//    KDateTime kDateTime = new KDateTime(calendar.getTime());
    try {
      String temp = Kalendar.KTimestampToString(KDateTime.FP_STANDARD,kTimestamp);
      KDateTime kDateTime = Kalendar.StringToKDateTime(KDateTime.FP_STANDARD,temp);
      return kDateTime;
    } catch (ParseException ex) {
      return null;
    }
  }

  static public KTime KTimestampToKTime(KTimestamp kTimestamp){
//    Calendar calendarTimestamp = new GregorianCalendar();
//    calendarTimestamp.setTime(kTimestamp);
//    Calendar calendar = new GregorianCalendar(0,
//                                              0,
//                                              0,
//                                              calendarTimestamp.get(Calendar.HOUR),
//                                              calendarTimestamp.get(Calendar.MINUTE),
//                                              calendarTimestamp.get(Calendar.SECOND));
//    KTime kTime = new KTime(calendar.getTime());
//    return kTime;
    try {
      String temp = Kalendar.KTimestampToString(KTime.FP_STANDARD,kTimestamp);
      KTime kTime = Kalendar.StringToKTime(KTime.FP_STANDARD,temp);
      return kTime;
    } catch (ParseException ex) {
      return null;
    }
  }

/**
 * ������
 */

  /**
   * ��ȡ��ǰʱ�䣬����KTimestamp��
   */
  static public KTimestamp getCurrentTimestamp(){
    java.util.Date date = new java.util.Date(System.currentTimeMillis());
    return new KTimestamp(date);
  }

  static public KTimestamp getCurrentTimestamp(String strTimeZone) {
	  try {
		return KTimestamp.getKTimestamp(new java.util.Date(),TimeZone.getTimeZone(strTimeZone));
	} catch ( ParseException e ) {
		e.printStackTrace();
		return null;
	}
  }  
  
  static public KTimestamp getKtimestamp( long timeMillis, String strTimeZone){
	  try {
		return KTimestamp.getKTimestamp( new KTimestamp( timeMillis ) ,TimeZone.getTimeZone(strTimeZone));
	} catch ( ParseException e ) {
		e.printStackTrace();
		return null;
	}
  }
  
  /**
   * ��ȡ��ǰʱ�䣬����KDate��
   */
  static public KDate getCurrentDate(){
    KTimestamp kt = Kalendar.getCurrentTimestamp();
    return Kalendar.KTimestampToKDate(kt);
  }

  static public KDate getCurrentDate(String strTimeZone)throws java.text.ParseException{
	    KTimestamp kt = Kalendar.getCurrentTimestamp();
		  if(strTimeZone!=null && !strTimeZone.equals(""))
			  kt=getCurrentTimestamp(strTimeZone);
	    return Kalendar.KTimestampToKDate(kt);
	  }  
  
  /**
   * ��ȡ��ǰʱ�䣬����KTime��
   */
  static public KTime getCurrentTime(){
    KTimestamp kt = Kalendar.getCurrentTimestamp();
    return Kalendar.KTimestampToKTime(kt);
  }
  
  static public KTime getCurrentTime(String strTimeZone)throws java.text.ParseException{
	    KTimestamp kt = Kalendar.getCurrentTimestamp();
		  if(strTimeZone!=null && !strTimeZone.equals("")){
			  kt=getCurrentTimestamp(strTimeZone);
		   } 	    
	    return Kalendar.KTimestampToKTime(kt);
  }
  
  /**
   * ��ȡ��ǰʱ�䣬����KDateTime��
   */
  static public KDateTime getCurrentDateTime(){
    KTimestamp kt = Kalendar.getCurrentTimestamp();
    return Kalendar.KTimestampToKDateTime(kt);
  }
  
  static public KDateTime getCurrentDateTime(String strTimeZone)throws java.text.ParseException{
	  KTimestamp kt = Kalendar.getCurrentTimestamp();  
	  if(strTimeZone!=null && !strTimeZone.equals("")){
		  kt=getCurrentTimestamp(strTimeZone);
	   }  
	  return Kalendar.KTimestampToKDateTime(kt);
  }
  
  /**
   * �õ���ĳ�쿪ʼn���ǰ�������ڣ�����KDate
   */
  static public KDate getKDateAfterDays(KDate StartKDate,int days){
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(StartKDate);
    calendar.add(Calendar.DATE,days);
    return new KDate(calendar.getTime());
  }

  /**
   * �õ���ĳ�쿪ʼn���ǰ�������ڣ�����KDateTime
   */
  static public KDateTime getKDateAfterDays(KDateTime StartKDate,int days){
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(StartKDate);
    calendar.add(Calendar.DATE,days);
    return new KDateTime(calendar.getTime());
  }


  static public KDateTime getKDateAfterMins(KDateTime StartKDate,int mins){
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(StartKDate);
    calendar.add(Calendar.MINUTE,mins);
    return new KDateTime(calendar.getTime());
  }
  
  static public KTimestamp getKTimestampAfterDays(KTimestamp StartKDate,int days){
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(StartKDate);
	    calendar.add(Calendar.DATE,days);
	    return new KTimestamp(calendar.getTime());
  }

  /**
   * �õ���ĳ�쿪ʼn�º�ǰ�������ڣ�����KDate
   */
  static public KDate getKDateAfterMonths(KDate StartKDate,int months){
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(StartKDate);
    calendar.add(Calendar.MONTH,months);
    return new KDate(calendar.getTime());
  }

  /**
   * �õ���ĳ�쿪ʼn�º�ǰ�������ڣ�����KDateTime
   */
  static public KDateTime getKDateAfterMonths(KDateTime StartKDate,int months){
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(StartKDate);
    calendar.add(Calendar.MONTH,months);
    return new KDateTime(calendar.getTime());
  }

  /**
   * �õ���ĳ�쿪ʼn�ܺ�ǰ�������ڣ�����KDate
   */
  static public KDate getKDateAfterWeeks(KDate StartKDate,int weeks){
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(StartKDate);
    int days = weeks * 7;
    calendar.add(Calendar.DATE,days);
    return new KDate(calendar.getTime());
  }

  /**
   * �õ���ĳ�쿪ʼn���ǰ�������ڣ�����KDate
   */
  static public KDate getKDateAfterYears(KDate StartKDate,int years){
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(StartKDate);
    calendar.add(Calendar.YEAR,years);
    return new KDate(calendar.getTime());
  }
  /**
   * �ж�һ��ʱ���Ƿ���һ��ʱ���ڣ���λ���գ�����(�߽�
   */
  static public boolean isBetween(KDate when, KDate start, KDate end){
     if(start==null)
    	 start = KDate.toKDate("1900-01-01");
     if(end == null)
    	 end = KDate.toKDate("9999-01-01");
	  if (when.after(start) && when.before(end)){
      return true;
    }
    return false;
  }
  
  static public boolean isEqualBetween(KDate when, KDate start, KDate end){
	     if(start==null)
	    	 start = KDate.toKDate("1900-01-01");
	     if(end == null)
	    	 end = KDate.toKDate("9999-01-01");
	      if(getDaysBetween(when, start)==0 || getDaysBetween(when, end)==0) return true;
		  if (when.after(start) && when.before(end)){
			  return true;
	      }
	    return false;
}
  
  /**
   * �ж�һ��ʱ���Ƿ���һ��ʱ���ڣ���λ���룬����(�߽�
   */
  static public boolean isBetween(KDateTime when, KDateTime start, KDateTime end){
    if (when.after(start) && when.before(end)){
      return true;
    }
    return false;
  }
 
  /**
   * ����}��ʱ���ڼ����������Ǹ����ʾ��ʼ���ڱȽ��������?
   * ���Ҫ��������������ڵ��ú���abs����
   */
  static public int  getDaysBetween(java.util.Date start, java.util.Date end){
    /*
	long ld = end.getTime() - start.getTime();
    long milSecondInADay = 1000*60*60*24;
    int  days = (new Long(ld/milSecondInADay)).intValue();
    return days;
    */
	KDate kstart = new KDate(start);
	KDate kend = new KDate(end);
	Calendar cstart = new GregorianCalendar( Integer.parseInt(kstart.toString().substring(0,4))
			, Integer.parseInt(kstart.toString().substring(5,7))-1
			, Integer.parseInt(kstart.toString().substring(8,10)));
	cstart.setTimeZone(TimeZone.getTimeZone("Hongkong")); 
	Calendar cend = new GregorianCalendar( Integer.parseInt(kend.toString().substring(0,4))
			, Integer.parseInt(kend.toString().substring(5,7))-1
			, Integer.parseInt(kend.toString().substring(8,10)));
	cend.setTimeZone(TimeZone.getTimeZone("Hongkong")); 
	long ld = cend.getTimeInMillis() - cstart.getTimeInMillis();
    long milSecondInADay = 1000*60*60*24;
    int  days = (new Long(ld/milSecondInADay)).intValue();
    return days;
  }
  
  static public double getMonthesBetween(KDate start, KDate end, boolean isThirty){
	    double months = 0;
	    boolean isNagtive = false;
	    if(Kalendar.getDaysBetween(start,end)<0) {
	    	isNagtive = true;
	    	KDate temp = start;
	    	start = end;
	    	end = temp;
	    }
	    //same month
	    do{
	    	int days = Kalendar.getDaysBetween(start,
	    			Kalendar.getDaysBetween(Kalendar.getLastDayOfMonth(start),end)>=0?Kalendar.getLastDayOfMonth(start):end
	    			)+1;
	    	/*
	    	int monthDays = 30;
	    	if(!isThirty)
	    		monthDays = Kalendar.getDaysBetween(Kalendar.getFirstDayOfMonth(start),Kalendar.getLastDayOfMonth(start))+1;
	    	months = months + days/(monthDays*1.0);
	    	*/
	    	int intThirtyDays = 30;
	    	int kMonthDays = Kalendar.getDaysBetween(Kalendar.getFirstDayOfMonth(start),Kalendar.getLastDayOfMonth(start))+1;
	    	if(days == kMonthDays)//whole month
	    		months ++ ;
	    	else
		    	months +=  1.0* days / (isThirty ? intThirtyDays: kMonthDays);
	    	//set start to next month's first day
	    	start = Kalendar.getKDateAfterDays(Kalendar.getLastDayOfMonth(start),1);
	    } while(Kalendar.getDaysBetween(start,end)>= 0);
	    
	    if(isNagtive){
	    	return -months;
	    }else{
	    	return months;
	    }
 }
  
  static public int  getWorkingDays(KDate sDate,KDate eDate){
		int intDays=0;
		while(Kalendar.getDaysBetween(sDate,eDate)>=0){
			if(!Kalendar.isWeekend(sDate))
				intDays++;
			sDate=Kalendar.getKDateAfterDays(sDate,1);
		}		
		return intDays;
 }  

 static public int  getDaysBetween(String start, String end){
  KDate kDateStart=KDate.toKDate(start);
  KDate kDateEnd=KDate.toKDate(end);

//   long ld = kDateEnd.getTime() - kDateStart.getTime();
//  long milSecondInADay = 1000*60*60*24;
//  int  days = (new Long(ld/milSecondInADay)).intValue();
//  return days;
  return getDaysBetween(kDateStart,kDateEnd);
}
 
 static public int  getDaysBetween(String format,String start, String end){
	  KDate kDateStart=KDate.toKDate(format,start);
	  KDate kDateEnd=KDate.toKDate(format,end);

//	   long ld = kDateEnd.getTime() - kDateStart.getTime();
//	  long milSecondInADay = 1000*60*60*24;
//	  int  days = (new Long(ld/milSecondInADay)).intValue();
//	  return days;
	  return getDaysBetween(kDateStart,kDateEnd); 
}
 
 /**
  * ����}��ʱ��εĽ������������0��ʾ}��ʱ���û�н���?<br>
  * �������߽�!<br>
  * ��ظ������鿪ʼ���ںͽ������ڵĲ���˳��?<br>
  * @param format such as "yyyy-MM-dd" "yyyy/MM/dd" "yyyyMMdd"
  * @param start1
  * @param end1
  * @param start2
  * @param end2
  */
 static public int  getDaysIntersection(String format,String start1, String end1,String start2, String end2){
	  KDate s1=KDate.toKDate(format,start1);
	  KDate e1=KDate.toKDate(format,end1);
	  KDate s2=KDate.toKDate(format,start2);
	  KDate e2=KDate.toKDate(format,end2);
      if(s2.after(s1)||s2.equals(s1)){
    	  if(e1.after(e2)) return getDaysBetween(format,start2,end2)+1;
    	  else  return getDaysBetween(format,start2,end1)+1;
      }else if(s2.before(s1)&& (s1.before(e2)||s1.equals(e2))){
    	  if(e1.after(e2)) return getDaysBetween(format,start1,end2)+1;
    	  else  return getDaysBetween(format,start1,end1)+1;
      }else{
    	  return 0;
      }
}

 /**
  * Return a number in specified time unit between the two date.<br>
  * timeUnit  <br>
  * ----------------------------------<br> 
  * day:  1000*60*60*24L     <br> 
  * hour:  1000*60*60L  <br> 
  * minute:  1000*60L <br> 
  * seconde:   1000L <br> 
  * @param sDate
  * @param eDate
  * @param timeUnit long
  * @return int
  */
 public static int between(java.util.Date sDate,java.util.Date eDate,long timeUnit ){
	 return ( new Long( (eDate.getTime()-sDate.getTime())/timeUnit)).intValue();
 }
 
 //fmt: yyyy.MM.dd HH:mm:ss
 public static int between(String fmt,String sDate,String eDate,long timeUnit ){
	 return between(KDate.toKDate(fmt,sDate),KDate.toKDate(fmt,eDate),timeUnit);
 }
 
  /**
   * �����ĳ�쿪ʼ�ĵڼ�����������һ����?
   * startDate ��ʼ����
   * n  �ڼ������գ�����Ϊ�����ʾ֮ǰ�ڼ������ա�?
   * @todo  not implement
   */
  public static KDate getTradeDate(KDate startDate,int n){
    return null;
  }

  public static KDate getFirstDayOfMonth(KDate kDate){
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(kDate);
    //��Ϊ1��
    calendar.set(Calendar.DATE,1);
    KDate firstDay = new KDate(calendar.getTime());
    return firstDay;
  }

//  public static KDate getFirstDayOfWeek(KDate kDate){
//    Calendar calendar = new GregorianCalendar();
//    calendar.setTime(kDate);
//    int iWeek=Kalendar.toIntOfWeek(kDate);
//    int step=0;
//    if(iWeek==7) step=-6;
//    if(iWeek==1) step=2-iWeek-7;
//    else step=2-iWeek;
//    KDate firstDay =Kalendar.getKDateAfterDays(kDate,step);
//    return firstDay;
//  }
  
  //change to first is Sunday
  public static KDate getFirstDayOfWeek(KDate kDate){
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(kDate);
	    int iWeek=Kalendar.toIntOfWeek(kDate);
	    int step=0;
	    if(iWeek==1) step=0;
	    else step=1-iWeek;
	    KDate firstDay =Kalendar.getKDateAfterDays(kDate,step);
	    return firstDay;
	 }  
  
//  public static KDate getLastDayOfWeek(KDate kDate){
//    Calendar calendar = new GregorianCalendar();
//    calendar.setTime(kDate);
//    int iWeek=Kalendar.toIntOfWeek(kDate);
//    int step=0;
//    if(iWeek==1) step=0;
//    else step=8-iWeek;
//    KDate firstDay =Kalendar.getKDateAfterDays(kDate,step);
//    return firstDay;
//  }
  
  //last day is Saturday
  public static KDate getLastDayOfWeek(KDate kDate){
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(kDate);
	    int iWeek=Kalendar.toIntOfWeek(kDate);
	    int step=0;
	    if(iWeek==7) step=0;
	    else step=7-iWeek;
	    KDate firstDay =Kalendar.getKDateAfterDays(kDate,step);
	    return firstDay;
	  }


  public static KDate getLastDayOfMonth(KDate kDate){
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(kDate);
    //��Ϊ��һ����
    calendar.add(Calendar.MONTH,1);
    //��Ϊ1��
    calendar.set(Calendar.DATE,1);
    //����һ����1��֮ǰ��һ�죬Ҳ���Ǳ������һ��?
    calendar.add(Calendar.DATE,-1);
    KDate lastDay = new KDate(calendar.getTime());
    return lastDay;
  }




  public static String getChCurrentDate() {
    int _y,_m,_d = 0;
    java.util.Calendar _calendar = java.util.Calendar.getInstance();
    _d = _calendar.get(java.util.Calendar.DAY_OF_MONTH);
    _m = _calendar.get(java.util.Calendar.MONTH) + 1;
    _y = _calendar.get(java.util.Calendar.YEAR);
    return ""+_y+"��"+_m+"��"+_d+"��";
  }

  public static String getChCurrentDateTime(KDateTime kDateTime ) {
  int _y,_m,_d,_h,_min,_s = 0;
  Calendar _calendar = new GregorianCalendar();
    _calendar.setTime(kDateTime);
  _d = _calendar.get(java.util.Calendar.DAY_OF_MONTH);
  _m = _calendar.get(java.util.Calendar.MONTH) + 1;
  _y = _calendar.get(java.util.Calendar.YEAR);
  _h = _calendar.get(java.util.Calendar.HOUR_OF_DAY);
  _min = _calendar.get(java.util.Calendar.MINUTE);
  _s = _calendar.get(java.util.Calendar.SECOND);
  return ""+_y+"��"+_m+"��"+_d+"��"+" "+_h+"ʱ"+_min+"��"+_s+"��";
}



  public static int getHour(KDateTime kDateTime) {
    Calendar _calendar = new GregorianCalendar();
    _calendar.setTime(kDateTime);
    return _calendar.get(java.util.Calendar.HOUR_OF_DAY);
  }

  public static int getHour(String strDateTime) {
    return getHour(KDateTime.toKDateTime(strDateTime));
  }

  public static int getMin(KDateTime kDateTime) {
    Calendar _calendar = new GregorianCalendar();
    _calendar.setTime(kDateTime);
    return _calendar.get(java.util.Calendar.MINUTE);
  }

  public static int getMin(String strDateTime) {
    return getMin(KDateTime.toKDateTime(strDateTime));
  }

  public static int getSecond(KDateTime kDateTime) {
    Calendar _calendar = new GregorianCalendar();
    _calendar.setTime(kDateTime);
    return _calendar.get(java.util.Calendar.SECOND);
  }

  public static int getSecond(String strDateTime) {
    return getSecond(KDateTime.toKDateTime(strDateTime));
  }


  public static int getDay(KDateTime kDateTime) {
    Calendar _calendar = new GregorianCalendar();
    _calendar.setTime(kDateTime);
    int _y, _m, _d = 0;
    return _calendar.get(java.util.Calendar.DAY_OF_MONTH);
  }

  public static int getDay(String strDateTime) {
    return getDay(KDateTime.toKDateTime(strDateTime));
  }





  public static String toDayOfWeek() {
    java.util.Calendar _calendar = java.util.Calendar.getInstance();
    int _x = _calendar.get(java.util.Calendar.DAY_OF_WEEK);
    String[] _days = new String[] {"������", "����һ", "���ڶ�", "������", "������", "������", "������"};
    if (_x > 7) return "Unknown to Man";
    return _days[_x - 1];
  }

  public static String toDayOfWeek(KDate kDate) {
    Calendar _calendar = new GregorianCalendar();
    _calendar.setTime(kDate);
    int _x = _calendar.get(java.util.Calendar.DAY_OF_WEEK);
    String[] _days = new String[] {"������", "����һ", "���ڶ�", "������", "������", "������", "������"};
    if (_x > 7) return "Unknown to Man";
    return _days[_x - 1];
  }

  public static int toIntOfWeek(KDate kDate) {
    Calendar _calendar = new GregorianCalendar();
    _calendar.setTime(kDate);
    return  _calendar.get(java.util.Calendar.DAY_OF_WEEK);
  }

  public static int toIntOfWeek(String strKDate) {
    Calendar _calendar = new GregorianCalendar();
    _calendar.setTime(KDate.toKDate(strKDate));
    return  _calendar.get(java.util.Calendar.DAY_OF_WEEK);
  }

  public static boolean isWeekend(KDate kDate){
	  int index = toIntOfWeek(kDate);
	  if(index==1 || index==7)
		  return true;
	  else
		  return false;
  }

  public static String toDayOfWeek(String strDate) {
    KDate kDate=KDate.toKDate(strDate);
    return toDayOfWeek(kDate);
  }



  public static int toIntDayOfWeek() {
  java.util.Calendar _calendar = java.util.Calendar.getInstance();
  int _x = _calendar.get(java.util.Calendar.DAY_OF_WEEK);
  return _x;
}


  public String toMonthOfYear() {
    java.util.Calendar _calendar = java.util.Calendar.getInstance();
    int _m = _calendar.get(java.util.Calendar.WEEK_OF_MONTH) + 1;
    String[] _months = new String [] { "һ��", "����", "����", "����", "����", "����",
                                      "����", "����", "����", "ʮ��", "ʮһ��", "ʮ����" };
    if (_m > 12) return "Unknown to Man";
    return _months[_m - 1];
  }
  
  
  public static Object dispDate(Object obj){
    if(obj==null || obj.toString().equals("")){
        return Kalendar.getCurrentDate().toString();
    }else{
        return obj;
    }
  }

 

  /**
   *  it is not allow to define const here!
   */
  private static String DATEFORMAT = "yyyy-mm-dd";
  private static String TIMEFORMAT = "HH:mm:ss";
  private static String TIMESTAMPFORMAT = "yyyy-mm-dd HH:mm:ss";


  /**
   * ��ʽ����������, �����ʽ�?yyyymmdd �� yyyy-mm-dd
   * �������ڸ�ʽΪ: yyyy-mm-dd
   */
  public static String formatDate(String date) {
    int _len = date.length();
    int _index = date.indexOf('-');
    String _date = null;

    if (_index < 0 && _len == 8) {
      _date = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
    }
    else if (_index > 0 && _len == 10) {
      return date;
    }
    else {
      throw new java.lang.IllegalArgumentException("");
    }

    return _date;
  }
  /**
   * get kdate after n months?return KDate
   */
  static public KDate getKDateAfterMonths(String strKDate,int months){
    return getKDateAfterMonths(KDate.toKDate(strKDate),months);

  }
  
  static public String getKDateAfterMonths(String fmt, String strKDate,int months){
	    return Kalendar.KDateToString(fmt,getKDateAfterMonths(KDate.toKDate(fmt,strKDate),months));
  }
  
  static public int getDay(KDate date){
	  String day=date.toString().substring(8,10);
	  return Integer.parseInt(day.startsWith("0")?day.substring(1,2):day);
  }
  
  static public boolean isFirstDayofMonth(KDate date){
	  return Kalendar.getDaysBetween(Kalendar.getFirstDayOfMonth(date), date)==0? true:false;
  }
  
  static public boolean isEndDayofMonth(KDate date){
	  return Kalendar.getDaysBetween(Kalendar.getLastDayOfMonth(date), date)==0? true:false;
  }
  
  static public boolean isSameMonth(KDate date1,KDate date2){
	  return Kalendar.getDaysBetween(Kalendar.getFirstDayOfMonth(date1), Kalendar.getFirstDayOfMonth(date2))
	  ==0 ? true:false;
  }
  
  @SuppressWarnings("deprecation")
public static void main(String[] argv){
	  try{
//		  System.out.println(Kalendar.getWorkingDays(KDate.toKDate("2008-09-25"),KDate.toKDate("2008-09-25")) );
//		  System.out.println(Kalendar.getWorkingDays(KDate.toKDate("2008-09-25"),KDate.toKDate("2008-09-30")) );
//		  System.out.println(Kalendar.getWorkingDays(KDate.toKDate("2008-09-27"),KDate.toKDate("2008-11-2")) );
//	  System.out.println(""+Kalendar.isWeekend(KDate.toKDate("2007-05-05")));	
//	  System.out.println(""+Kalendar.isWeekend(KDate.toKDate("2007-05-06")));
//	  System.out.println("Amarican="+Kalendar.getCurrentDateTime("US/Pacific"));
//	  System.out.println("Amarican="+Kalendar.getCurrentDate("US/Pacific"));
//	  System.out.println("Amarican="+Kalendar.getCurrentTime("US/Pacific"));
//	  System.out.println("Shanghai="+Kalendar.getCurrentDateTime("Asia/Shanghai"));
//	  System.out.println("Shanghai="+Kalendar.getCurrentDate("Asia/Shanghai"));
//	  System.out.println("Shanghai="+Kalendar.getCurrentTime("Asia/Shanghai"));	 
//	  System.out.println("="+Kalendar.getCurrentDateTime());
//	  System.out.println("="+Kalendar.getCurrentDate());
//	  System.out.println("="+Kalendar.getCurrentTime());
//	  System.out.println("cur date Amarican="+Kalendar.getCurrentDate("US/Pacific"));
//	  System.out.println(Kalendar.getMonthesBetween(KDate.toKDate("2008-09-25"),KDate.toKDate("2008-09-25"),false));	 
//	  System.out.println(Kalendar.getMonthesBetween(KDate.toKDate("2008-09-25"),KDate.toKDate("2008-10-24"),false));	
//	  System.out.println(Kalendar.getMonthesBetween(KDate.toKDate("2008-09-25"),KDate.toKDate("2008-11-24"),false));	
//	  System.out.println(Kalendar.getMonthesBetween(KDate.toKDate("2008-09-01"),KDate.toKDate("2008-09-30"),false));
//	  System.out.println(Kalendar.getMonthesBetween(KDate.toKDate("2008-09-01"),KDate.toKDate("2008-10-1"),false));
//	  System.out.println(Kalendar.getMonthesBetween(KDate.toKDate("2008-09-01"),KDate.toKDate("2008-12-31"),false));
//	  System.out.println(Kalendar.getMonthesBetween(KDate.toKDate("2008-09-01"),KDate.toKDate("2010-08-31"),false));
//	  System.out.println(Kalendar.getMonthesBetween(KDate.toKDate("2008-05-01"),KDate.toKDate("2008-05-30"),true));
//	  System.out.println(Kalendar.getMonthesBetween(KDate.toKDate("2008-05-01"),KDate.toKDate("2008-05-30"),false));
//	  System.out.println(Kalendar.getMonthesBetween(KDate.toKDate("2008-02-01"),KDate.toKDate("2008-02-29"),true));
//	  System.out.println(Kalendar.getMonthesBetween(KDate.toKDate("2008-02-01"),KDate.toKDate("2008-02-29"),false));
//	  System.out.println(Kalendar.getMonthesBetween(KDate.toKDate("2011-03-01"),KDate.toKDate("2011-03-05"),false));
	  System.out.println(Kalendar.getMonthesBetween(KDate.toKDate("2011-03-01"),KDate.toKDate("2011-03-31"),false));
	  System.out.println(Kalendar.getMonthesBetween(KDate.toKDate("2011-03-01"),KDate.toKDate("2011-03-31"),true));	 
	  System.out.println(Kalendar.getMonthesBetween(KDate.toKDate("2011-01-01"),KDate.toKDate("2011-03-31"),false));
	  System.out.println(Kalendar.getMonthesBetween(KDate.toKDate("2011-01-01"),KDate.toKDate("2011-03-31"),true));	  
//	  System.out.println("<br>endtime31:"+KDate.toKDate("2011-03-31").getTime());
//	  System.out.println("<br>endtime2:"+KDate.toKDate("2011-03-02").getTime());
//	  System.out.println("<br>endtime11:"+KDate.toKDate("2011-03-11").getTime());
//	  System.out.println("<br>endtime12:"+KDate.toKDate("2011-03-12").getTime());
//	  System.out.println("<br>endtime13:"+KDate.toKDate("2011-03-13").getTime());
//	  System.out.println("<br>endtime14:"+KDate.toKDate("2011-03-14").getTime());
//	  System.out.println("<br>endtime700101:"+KDate.toKDate("1970-01-01").getTime());
//	  System.out.println("<br>endtime700101:"+new Date(70,0,1).getTime());
//	  System.out.println("<br>endtime700102:"+KDate.toKDate("1970-01-02").getTime());
	  //System.out.println(Kalendar.isBetween(KDate.toKDate("2008-01-01"),null,null));
	  //Kalendar.getKDateAfterDays(StartKDate, days)
//	   System.out.println(Kalendar.getDaysBetween(KDate.toKDate("2011-03-11"),KDate.toKDate("2011-03-14")));
//	   System.out.println(Kalendar.getDaysBetween(KDate.toKDate("2011-03-01"),KDate.toKDate("2011-03-14")));
//	   System.out.println(Kalendar.getDaysBetween(KDate.toKDate("2011-03-01"),KDate.toKDate("2012-03-01")));
	  }catch(Exception e){}
  }
  
  
  
}