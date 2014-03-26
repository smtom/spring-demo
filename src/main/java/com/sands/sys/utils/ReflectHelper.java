/*
 * Created on 2005-1-21
 *
 * @author Stephen Ding
 * v1.0	 
 */
package com.sands.sys.utils;


import java.sql.*;
import java.util.*;
import java.math.*;
import java.lang.reflect.*;

import com.sands.sys.date.KDate;
import com.sands.sys.date.KTimestamp;
import com.sands.sys.utils.StrUtils;






public class ReflectHelper {
	
	/**
	 * translate Map to persistance Object
	 * @param map 
	 * @param cls persistance Object's Java Class name
	 * @return persistance Object
	 * @throws AppException
	 */
	public static Object map2Obj(Map map,Class cls){
		Object obj=null;
		
		try{
			obj = cls.newInstance();
			Iterator it=map.entrySet().iterator();
			Map.Entry entry=null;
			while(it.hasNext()){
				entry=(Map.Entry)it.next();
				ReflectHelper.eval(obj,new Object[]{entry.getValue()},"set"+tranFirstCtoUpper(entry.getKey().toString()));
			}		
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("translate map to Hibernate Object error:"+e.getMessage());
		} 
		return obj;
	}
	

	/**
	 * transfor the first Charactar of str to Upcase charactar
	 * @param str
	 * @return
	 */
	   public static String tranFirstCtoUpper(String str) {
	    String first_c = str.charAt(0) + "";
	    first_c = first_c.toUpperCase();
	    return first_c + str.substring(1, str.length());
	  }	
	
	     /**
	      *
	      * @param instance:Ҫִ�еĶ�������
	      * @param params:Ҫִ�з�������Ҫ�Ĳ���
	      * @param methodName��Ҫִ�еķ������?
		  * @param checkParamType:�Ƿ�Ҫ�Բ������ͽ���У��
	      * @return��ִ�з�����ķ��ؽ�� 
	      * @throws Exception
	      */

	     public static Object eval(Object instance,Object[] params,String methodName,boolean checkParamType)throws Exception{
	      Class cls = instance.getClass();
	      java.lang.reflect.Method method = null;
	      java.lang.reflect.Method[] methods = cls.getMethods();
	      for (int i = 0; i < methods.length; i++) {
	          if (methods[i].getName().equalsIgnoreCase(methodName)) {
	            method = methods[i];
	            break;
	          }
	       }
	     
	      try{
	        if (method != null) {
			   if(checkParamType){
	              params = getRightParamType(params,method);
			   }
	           Object obj=method.invoke(instance, params);
	         
	          return obj;
	        }
	      }catch(Exception e){
	        e.printStackTrace();
	        throw new Exception(e);
	      }
	      return null;
	    }	
	
	/**
    *
    * @param cls:Ҫִ�е�Class����
    * @param params:Ҫִ�з�������Ҫ�Ĳ���
    * @param methodName��Ҫִ�еķ������?
    * @return��ִ�з�����ķ��ؽ��
    * @throws YZException
    */
   public static Object eval(Class cls,Object[] params,String methodName)throws Exception{
     java.lang.reflect.Method method = null;
     if(params != null){
       java.lang.reflect.Method[] methods = cls.getMethods();
       for (int i = 0; i < methods.length; i++) {
         if (methods[i].getName().equalsIgnoreCase(methodName)) {
           method = methods[i];
           break;
         }
       }
     }else{
       method = cls.getMethod(methodName,null);
     }
     try{
       if (method != null) {
         Object obj = cls.newInstance();
         params = getRightParamType(params,method);
         return method.invoke(obj, params);
       }
     }catch(Exception e){
       throw new Exception(e);
     }
     return null;
   }

   /**
    *
    * @param instance:Ҫִ�еĶ�������
    * @param params:Ҫִ�з�������Ҫ�Ĳ���
    * @param methodName��Ҫִ�еķ������?
    * @return��ִ�з�����ķ��ؽ��
    * @throws YZException
    */

   public static Object eval(Object instance,Object[] params,String methodName)throws Exception{
    //log.debug("call eval(Object instance,Object[] params,String methodName)");
     Class cls = instance.getClass();
    java.lang.reflect.Method method = null;
    if(params != null){
      java.lang.reflect.Method[] methods = cls.getMethods();
      for (int i = 0; i < methods.length; i++) {
        if (methods[i].getName().equalsIgnoreCase(methodName)) {
          method = methods[i];
          break;
        }
      }
    }else{
      method = cls.getMethod(methodName,null);
    }
    try{
      if (method != null) {
        //log.debug("beginning call getRightParamType in eval()");
        params = getRightParamType(params,method);
        //log.debug("end call getRightParamType in eval()");
        //log.debug("beginning call method.invoke(instance, params) in eval()");
        //return method.invoke(instance, params);
        Object obj=method.invoke(instance, params);
        //log.debug("end call method.invoke(instance, params) in eval()");
        return obj;
      }
    }catch(Exception e){
      //log.debug("------error in eval()");
      e.printStackTrace();
      throw new Exception(e);
    }
    return null;
  }

  /**
   *
   * @param instance:Ҫִ�еĶ�������
   * @param params:Ҫִ�з�������Ҫ�Ĳ���
   * @param methodName��Ҫִ�еķ������?
   * @return��ִ�з�����ķ��ؽ��
   * @throws YZException
   */

  public static Object eval(Object instance,Object params,String methodName)throws Exception{
    Object[] sObj=new Object[1];
    sObj[0]=params;
    if(params == null)
      sObj = null;
    return eval(instance,sObj,methodName);
 }
  
  public static Object eval(Object instance,String methodName)throws Exception{
	    return eval(instance,null,methodName);
  }
  
  public static Object evalGet(Object instance,String fieldName)throws Exception{
	    return eval(instance,null,"get"+StrUtils.upFirstCharacter(fieldName));
  }

  
  /**
   * ��ݴ���ķ���������Ĳ���ת����ʺϸ÷�������Ĳ�������?
   * �÷����ڷ������ʱʹ��?
   * ĳǰ�÷���ֻ�ṩ�˶�����������͵�ת��?
   * byte,Byte;short,Short;int:Integer;long:Long;String;TimeStamp;Double:double;BigDecmial
   * @param param:��Ҫת���Ĳ�������
   * @param method:�������?
   * @return
   */
  public static Object[] getRightParamType(Object[] param,Method method){
   if(param != null){
     Class[] clls = method.getParameterTypes();
     return getRightParamType(param,clls);
   }
   else {
     return null;
   }
 }
  
  
  public static Object[] getRightParamType(Object[] param,Class[] clls){
    Object[] result=new Object[param.length];
    for(int i=0;i<clls.length;i++){
       if(param[i]==null){
         result[i]=null;
         continue;
       }
       String typeName = clls[i].getName();
       int splitPoint = typeName.lastIndexOf(".");
       if (splitPoint > 0) {
         typeName = typeName.substring(splitPoint + 1, typeName.length());
       }
       if (typeName.equals("int")) {
         result[i] = new Integer(param[i].toString());
         continue;
       }
       if (typeName.equals("short")) {
         result[i] = new Short(param[i].toString());
         continue;
       }
       if (typeName.equals("long")) {
         result[i] = new Long(param[i].toString());
         continue;
       }
       if (typeName.equals("double")) {
         result[i] = new Double(param[i].toString());
         continue;
       }
       if (typeName.equals("Integer")) {
         result[i] = new Integer(param[i].toString());
         continue;
       }
       if (typeName.equals("Float")) {
         result[i] = new Float(param[i].toString());
         continue;
       }

       if (typeName.equals("BigDecimal")) {
         result[i] = new BigDecimal(param[i].toString());
         continue;
       }
       if (typeName.equals("BigInteger")) {
         result[i] = new BigInteger(param[i].toString());
         continue;
       }

       if (typeName.equals("Short")) {
         result[i] = new Short(param[i].toString());
         continue;
       }
       if (typeName.equals("Long")) {
         result[i] = new Long(param[i].toString());
         continue;
       }
       if (typeName.equals("Double")) {
         result[i] = new Double(param[i].toString());
         continue;
       }
       if (typeName.equals("Date")) {
           String dateStr = param[i].toString();
           System.out.println("dateStr="+dateStr);
    	   result[i] = dateStr!=null && dateStr.length() == 10 ? KDate.toKDate(dateStr) : null;
       }

       if (typeName.equals("Timestamp")) {
         String dateStr = param[i].toString();
         result[i] = getTimestamp(dateStr);
         continue;
       }else{
    	 System.out.println("type name="+typeName+",param[i]="+param[i]);  
         result[i]=param[i];
         continue;
       }
     }
     return result;
 }
  
  
  /**
   * ��ݸ����yy-mm-dd�ַ���һ��Timestamp����
   * @param dateStr �����ַ����Ϊ�շ��ص�ǰ���ڹ����Timestamp����
   * @return
   */
  public static Timestamp getTimestamp(String dateStr){
   if(dateStr == null){
     return new KTimestamp(System.currentTimeMillis());
   }else{
        try{
          StringTokenizer st = new StringTokenizer(dateStr, " ");
          String ymd = getCurShortDate();
          String hms = "00:00:0.0";
          if(st.hasMoreElements()){
            ymd = (String)st.nextElement();
            if(st.hasMoreElements()){
              hms = (String)st.nextElement();
            }
          }
          String[] YMD = ymd.split("-");
          String[] HMS = hms.split(":");
          Calendar cal = new GregorianCalendar(Integer.parseInt(YMD[0]),
                                              Integer.parseInt(YMD[1])-1,
                                              Integer.parseInt(YMD[2]),
                                              Integer.parseInt(HMS[0]),
                                              Integer.parseInt(HMS[1]),
                                              0);

          cal.setTimeZone(TimeZone.getTimeZone(
              "Asia/ShangHai"));
          return new Timestamp(cal.getTimeInMillis()+(int)((Float.parseFloat(HMS[2]))*1000));

        }catch(Exception e){
         //e.printStackTrace();
         return null;
        }
        }
 }
	
  
  /**
   * ��ȡ��ǰʱ��(��-��-��)
   * @return
   */
  public static String getCurShortDate(){
    Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("Asia/ShangHai"));
    KDate kdc = new KDate(cal.getTime());
    String date = kdc.toString();
    return date;
  }
  
  
  
  /**
   * �����ʵ������ݷ������ת���ɸ��Class���Ӧ�Ķ���?
   * ������Struts�е�JavaBean����ķ�����������һ�㶼�����ַ����ͣ�?
   * ���Ӧ��Entity��������ܰ����ԭʼ������ͣ������Ҫת��
   * @param obj JavaBean����
   * @param cls ��Ӧ��Entity��Class����
   * @return ת�����Entity����
   */
  public static Object formBean2Entity(Object obj,Class cls) throws Exception {
  	Method[] sMethods      = obj.getClass().getMethods();
  	Method[] tMethods      = cls.getMethods();
  	
  	String   methodName    = "";
  	String   methodSubName = "";
  	String   methodPreName = "get";
  	try{
	  	   Object toObject = cls.newInstance();
	  	   for(int  i=0;i<tMethods.length;i++){
	  		methodName = tMethods[i].getName();
	  		Object  value  = null;
	  		Class[] paType = null;
	  	
	  		if(methodName.startsWith("set")){	  			
	  			paType         = tMethods[i].getParameterTypes();
	  			methodSubName  = methodName.substring(3,methodName.length());
	  			if(paType != null){
	  				if(paType[0].getName().equalsIgnoreCase("boolean")){
	  					methodPreName  = "is";	  					
	  				}else{
	  				//if exist another naming regular please coding here
	  				   methodPreName = "get";	
	  				}
	  			    value = eval(obj,null,methodPreName+methodSubName,false);
	  			}else{
	  				;
	  			}	  			    
	  		}else{
	  			continue;
	  		}
	  		
	  		//set javabean properties to entitybean object
		  	if(paType != null && paType.length>0){
		  		Object[] para = new Object[1];	
		  		
		  		if(!paType[0].getName().endsWith("String") &&
		  			value.toString().trim().length()<1){
		  			/*to empty string, if entitybean need not is String type,
		  			 * because empty string can not tarnslate to another Type,
		  			 * so we should not to do it!
		  			 */
		  			;
		  		}else{
		  		   para[0] = toRightParamType(paType[0].getName(),value);
		  	       eval(toObject,para,"set"+methodSubName,false);	
		  		}
		     }
		  }
	  	  return toObject;
  	}catch(Exception e){
  		throw new Exception(e.getMessage());
  	}  	
  }
  
  
  public static Object merge1stEntityWithoutNull(Object firstEntity, Object secondEntity) {
	 return merge1stEntityWithoutNull(firstEntity,secondEntity,false);
  }
  
  public static Object merge1stEntityWithoutNull(Object firstEntity, Object secondEntity,boolean isTrim) {
	  	if(firstEntity == null)
	  		return firstEntity;
	  	Method[] methods      = firstEntity.getClass().getMethods();
	  	String   methodName    = "";
	  	String   methodSubName = "";
	  	String   methodPreName = "get";
	  	try{
		  	   for(int  i=0;i<methods.length;i++){
		  		methodName = methods[i].getName();
		  		Object  value  = null;
		  		Class[] paType = null;
		  	
		  		if(methodName.startsWith("set")){	  			
		  			paType         = methods[i].getParameterTypes();
		  			methodSubName  = methodName.substring(3,methodName.length());
		  			if(paType != null && paType.length>0){
		  				if(paType[0].getName().equalsIgnoreCase("boolean")){
		  					methodPreName  = "is";	  					
		  				}else{
		  				//if exist another naming regular please coding here
		  				   methodPreName = "get";	
		  				}
		  			    value = eval(secondEntity,null,methodPreName+methodSubName,false);
		  			    if(value != null){
		  			    	System.out.println(methodPreName+methodSubName +": "+value);
		  			    	if(isTrim && value instanceof String){
		  			    		value = ((String)value).trim();
		  			    	}
		  			    	Object[] para = new Object[1];	
					  		para[0] = toRightParamType(paType[0].getName(),value);
					  		if(para[0] != null)
					  			eval(firstEntity,para,"set"+methodSubName,false);	
					  	}
		  			}  			    
		  		}
		  	   }	
		  	  return firstEntity;
	  	}catch(Exception e){
	  		throw new RuntimeException(e.getMessage());
	  	}  	
	  }
  
  
  private static Object toRightParamType(String typeName,Object value){
  	 if(value == null)
  	 	return null;
  	 String vs = value+"";
  	 if(typeName.length()<1)
  	 	return null;
  	 
  	int splitPoint = typeName.lastIndexOf(".");
    if (splitPoint > 0) {
      typeName = typeName.substring(splitPoint + 1, typeName.length());
    }
  	 
  	 
  	 if(typeName.equalsIgnoreCase("int")   || typeName.equalsIgnoreCase("Integer"))
  	 	return new Integer(vs);
  	 if(typeName.equalsIgnoreCase("short") || typeName.equalsIgnoreCase("Short"))
  	    return new Short(vs);
  	if(typeName.equalsIgnoreCase("long")   || typeName.equalsIgnoreCase("Long"))
  		return new Long(vs);
  	if(typeName.equalsIgnoreCase("double") || typeName.equalsIgnoreCase("Double"))
  		return new Double(vs);  	
  	if(typeName.equalsIgnoreCase("float")  || typeName.equalsIgnoreCase("Float"))
  		return new Double(vs);
  	
  	if (typeName.equalsIgnoreCase("String")) 
        return vs;
  	
  	if (typeName.equals("BigDecimal")) 
        return new BigDecimal(vs);
      
    if (typeName.equals("BigInteger")) 
      	return new BigInteger(vs);
         	
  	if (typeName.equals("Timestamp")) 
        return getTimestamp(vs); 
  	
  	if (typeName.equals("Time")) 
        return java.sql.Time.valueOf( vs ); 
  	
  	if (typeName.equalsIgnoreCase("boolean")) 
        return new Boolean(vs); 
  	
  	if (typeName.equalsIgnoreCase("Date")){ 
        //return vs;
  		System.out.println("typeName="+typeName+",vs="+vs);
  		return vs!=null && vs.length() == 10 ? KDate.toKDate(vs) : null;
  	}
  	else
  		return null;
  	 
  }
  /**
   * copy some instance context to newClass-instance which extends from newClass 
   * @param newClass
   * @param adaptered
   * @return
   */
  public static Object superAdapter(Class newClass,Object adaptered){
    Object newInstance = null;    
  	try{		 
  		  newInstance = newClass.newInstance();
          Method[] methods = adaptered.getClass().getMethods();        
          for (int i = 0; i < methods.length; i++) {          
            if (methods[i].getName().trim().startsWith("get")) {
              Object para = methods[i].invoke(adaptered, null);
              //System.out.println(methods[i].getName()+"="+para);
              String methodName = "set"+methods[i].getName().trim().substring(3,methods[i].getName().length());
              Object[] pa = new Object[1];
              pa[0] = para;
              try{             
              	eval(newInstance,pa,methodName,false);
              }catch(Exception ex){              	
              	ex.printStackTrace();
              }
            }
          }
        }catch(Exception e){
          e.printStackTrace();
        }
	  return newInstance;
  }
  
  public static Object[] superAdapter(Class newClass,Object[] adaptered){
  	Object[] newInstances = new Object[adaptered.length];
  	for(int k=0;k<adaptered.length;k++){
  		newInstances[k] = superAdapter(newClass,adaptered[k]);
  	}
  	return newInstances;
  }
  
  /**
   * change formbean with String[] method to newClasss instance array
   * @param formbean
   * @param newClass
   * @return
   */
  public static Object[] superAdapter(Object formbean,Class newClass){
  	Object[] newInstance = null;
  	try{
  		if(newClass != null){
			Method[] methods = newClass.getDeclaredMethods();		
			String getName = "";
			String setName = "";
	        for (int i = 0; i < methods.length; i++) {
	        	getName = methods[i].getName().trim();
	        	setName = "s"+getName.substring(1,getName.length());
	          if (getName.startsWith("get")){
	              String[] para = (String[])eval(formbean,null,getName,false);
	              if(newInstance == null){
	              	newInstance = new Object[para.length];
	              	for(int k=0;k<newInstance.length;k++){
	              		newInstance[k] = newClass.newInstance();
	              	}
	              }             
	              for(int k=0;k<newInstance.length;k++){
	              	Object[] param = new Object[]{para[k]};
	              	eval(newInstance[k],param,setName,false);
	              }           
	          }
	        }
  		}else{  			
  			Method[] methods = formbean.getClass().getDeclaredMethods();		
			String getName = "";
			String setName = "";			
	        for (int i = 0; i < methods.length; i++) {
	        	getName = methods[i].getName().trim();
	        	setName = getName.substring(3,4).toLowerCase()+getName.substring(4,getName.length());
	            if (getName.startsWith("get") && methods[i].getReturnType().isArray()){
	            	String[] para = (String[])eval(formbean,null,getName,false);
	                if(newInstance == null){
		              	newInstance = new Object[para.length];
		              	for(int k=0;k<newInstance.length;k++){
		              		newInstance[k] = new HashMap();
		              	}
		              } 
	        	    
	      
	              for(int k=0;k<newInstance.length;k++){
	              	((HashMap)newInstance[k]).put(setName,para[k]);	              
	              }           
	          }
	        }
  			
  		}
        
      }catch(Exception e){
        e.printStackTrace();
      }      
	  return newInstance;
  }
  
  
	
}
