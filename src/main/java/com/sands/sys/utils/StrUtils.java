package com.sands.sys.utils;


public class StrUtils {
	
    /**
     *  e.g.: turns a's home into a''s home so that it could be inserted into oracle.
     */
  public static String conv(String inString) {
		StringBuffer result = new StringBuffer();
		if (inString == null) {
			return "";
		}
		int i;
		for (i = 0; i < inString.length(); i++) {
			if (inString.charAt(i) == '\'') {
				result.append("\'").append(inString.charAt(i));
			} else {
				result.append(inString.charAt(i));
			}
		}
		return result.toString();
	}
  
	public static String convSqlin( Object... objs ) {
		StringBuilder sb = new StringBuilder();
		for (Object object : objs) {
			sb.append( object ).append( "," );
		}
		return sb.length() > 0 ? 
				sb.deleteCharAt( sb.length()-1 ).toString() : sb.toString();
	}
  
  public static String replace(String oriStr,String replaceKey,String replaceTo){
	  if(oriStr == null || oriStr.length() == 0)
		  return oriStr;
	  return oriStr.replaceAll(replaceKey, replaceTo);
  }
  
  public static String replaceDashToEmpty(String oriStr){
	  return replace(oriStr,"-","");
  }
  
  
  public static String upFirstCharacter(String str){
	  if(str==null)
		  return null;
	  if(str.length()==1)
		  return str.toUpperCase();
	  else
		  return str.substring(0,1).toUpperCase()+str.substring(1,str.length());
  }
  
  public static void main(String[] argvs){
	  System.out.println(replace("643-2317-0","-",""));
	  System.out.println(replaceDashToEmpty("643-2317-00-90"));
	  System.out.println(convSqlin( 2, 3, 5 ));
  }

	public static String nullToEmpty(String str) {
		return str==null?"":str;
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}
	
      
}
