package com.sands.sys.utils;

import java.lang.reflect.Array;
import java.util.*;

public class ListUtil {
	
	public static String fieldValues(List<Map> list,String fieldName){
		if(list == null) return "";
		StringBuffer buff = new StringBuffer();
		Map map = null;
		for(int i=0;i<list.size();i++){
			map = (Map)list.get(i);
			if(map.get(fieldName) != null){
				buff.append(map.get(fieldName)).append(",");
			}
		}
		if(buff.length()>0)
			return buff.substring(0,buff.length()-1);
		else
			return "";
	}
	
	public static String fieldNEmptyValues(List<Map> list,String fieldName){
		if(list == null) return "";
		StringBuffer buff = new StringBuffer();
		Map map = null;
		for(int i=0;i<list.size();i++){
			map = (Map)list.get(i);
			if(map.get(fieldName) != null && !"".equals(map.get(fieldName))){
				buff.append(map.get(fieldName)).append(",");
			}
		}
		if(buff.length()>0)
			return buff.substring(0,buff.length()-1);
		else
			return "";
	}
	
	public static String[] fieldString2Array(List<Map> list,String fieldName){
		if(list == null) return new String[0];
		String[] result = new String[list.size()];
		Map map = null;
		for(int i=0;i<list.size();i++){
			map = (Map)list.get(i);
			if(map.get(fieldName) != null){
				result[i] = (String)map.get(fieldName);
			}else{
				result[i] = "";
			}
		}
		return result;
	}
	
	public static Object[] field2Array(List<Object> list,String fieldName){
		if(list == null) return new String[0];
		Object[] result = new Object[list.size()];
		Object obj = null;
		for(int i=0;i<list.size();i++){
			obj = list.get(i);
			if(obj instanceof Map)
				result[i] = ((Map)obj).get(fieldName);
			else
				try{
				result[i] = ReflectHelper.evalGet(obj,fieldName);
				}catch(Exception e){
					throw new RuntimeException(e.getMessage());
				}
		}
		return result;
	}
	
	public static String[] fieldNEmptyString2Array(List<Map> list,String fieldName){
		if(list == null) return new String[0];
		List result = new LinkedList();
		Map map = null;
		for(int i=0;i<list.size();i++){
			map = (Map)list.get(i);
			if(map.get(fieldName) != null && !"".equals(map.get(fieldName))){
				result.add(map.get(fieldName));
			}
		}
		return (String[])result.toArray(new String[0]);
	}
	
	public static Integer[] fieldInteger2Array(List<Map> list,String fieldName){
		if(list == null) return new Integer[0];
		Integer[] result = new Integer[list.size()];
		Map map = null;
		for(int i=0;i<list.size();i++){
			map = (Map)list.get(i);
			if(map.get(fieldName) != null){
				result[i] = new Integer((String)map.get(fieldName));
			}else{
				result[i] = 0;
			}
		}
		return result;
	}
	
//	public static Object[] split(List<Map> list, Object key){
//		if(list==null) return null;
//		List<List> result = new LinkedList<List>();
//		List<Map> splitList = new LinkedList<Map>();
//		Map map = null;
//		Object markValue = null;
//		for(int i = 0;i<list.size();i++){
//			map = (Map)list.get(i);
//			Object value = map.get(key);
//			if(markValue==null)
//				markValue = value;
//			if(value.equals(markValue))
//				splitList.add(map);
//			else{
//				result.add(splitList);
//				splitList = new LinkedList<Map>();
//				splitList.add(map);
//				markValue = value;
//			}
//			if(list.size()-1==i){
//				result.add(splitList);
//			}
//		}
//		return (Object[])result.toArray(new Object[0]);	
//	}
	
	public static Object[] split(List list, Object key){
		if(list==null) return null;
		List<List> result = new LinkedList<List>();
		List<Object> splitList = new LinkedList<Object>();
		Object instance = null;
		Object markValue = null;
		for(int i = 0;i<list.size();i++){
			instance = list.get(i);
			Object value = null;
			if(instance instanceof Map)
				 value = ((Map)instance).get(key);
			else{
				try{
					value = ReflectHelper.evalGet(instance,key.toString());
				}catch(Exception e){
					throw new RuntimeException(e.getMessage());
				}
			}
			if(markValue==null)
				markValue = value;
			if((value==null&&value==markValue)||(value!=null&&value.equals(markValue)))
				splitList.add(instance);
			else{
				result.add(splitList);
				splitList = new LinkedList<Object>();
				splitList.add(instance);
				markValue = value;
			}
			if(list.size()-1==i){
				result.add(splitList);
			}
		}
		return (Object[])result.toArray(new Object[0]);	
	}
	
	
	public static String[] strArrayOf(List list){
		if(list==null)
			return new String[]{};
		String[] s = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			s[i] = list.get(i).toString();
		}
		return s;
	}
	
	public static <T,O> void sortByKey(List<T> list,O... keys) {
		if(list.size()<2)
			return  ;
		if(keys.length<1)
			return ;
		List<T> copy  = new ArrayList<T>(list);
		mergeSort(copy, list, 0, list.size(), 0, keys[0]);
		for (int i = 1; i < keys.length; i++ ) {
			List<int[]> sameValueNdxes =  indexOfSameValue(list,copyOfRange(keys,0,i));
			copy  = new ArrayList<T>(list);
			for ( int[] ndx : sameValueNdxes )
				if( ndx[0] != ndx[1] )
					mergeSort(copy, list, ndx[0], ndx[1]+1 , 0, keys[i]);
		}
	}
	
	@SuppressWarnings("unchecked")
	private static <T,O> void mergeSort(List<T> src,List<T> dest, int low, int high, int off,O key) {

		int length = high - low;
		
		// Insertion sort on smallest arrays
		  if (length < 7) {
		      for (int i=low; i<high; i++)
		          for (int j=i; j>low &&( ( valueAt(j-1,dest,key)==null && valueAt(j,dest,key)!=null ) 
		        		  || (valueAt(j-1,dest,key)!=null &&valueAt(j,dest,key)!=null
		        				  && valueAt(j-1,dest,key).compareTo(valueAt(j,dest,key))>0 ) ) ; j--)
		              swap(dest, j, j-1);
		      return;
		  }
		
		  // Recursively sort halves of dest into src
		  int destLow  = low;
		  int destHigh = high;
		  low  += off;
		  high += off;
		  int mid = (low + high) >>> 1;
		  mergeSort(dest, src, low, mid, -off,key);
		  mergeSort(dest, src, mid, high, -off,key);
		
		  // If list is already sorted, just copy from src to dest.  This is an
		  // optimization that results in faster sorts for nearly ordered lists.
		  if ( (valueAt(mid-1,src,key)!=null && valueAt(mid,src,key)!=null && valueAt(mid-1,src,key).compareTo( valueAt(mid,src,key)) <= 0)
				 || (valueAt(mid-1,src,key) != null && valueAt(mid,src,key) ==null) ){
		      listcopy(src, low, dest, destLow, length);
		      return;
		  }
		
		  // Merge sorted halves (now in src) into dest
		  for(int i = destLow, p = low, q = mid; i < destHigh; i++) {
		      if (q >= high || p < mid &&( (valueAt(p,src,key)!=null && valueAt(q,src,key)==null )
		    		  || ( valueAt(p,src,key)!=null && valueAt(q,src,key)!=null  && valueAt(p,src,key).compareTo( valueAt(q,src,key) )<=0 ) )
		      )
		    	  dest.set(i, src.get(p++));
		      else
		    	  dest.set(i, src.get(q++));
		  }
	}
	
	private static <T,O> Comparable valueAt(int index, List<T> list, O key) {
		return valueAt( list.get(index), key ) ;
	}
	
	public static <T,O> Comparable valueAt(T obj , O key) {
		Comparable result = null;
		if (obj instanceof Map) 
			result = (Comparable)((Map)obj).get(key);
		else
			try {
				result = (Comparable)ReflectHelper.evalGet(obj, key.toString());
			} catch (Exception e) {
				System.err.println("No such field "+key.toString()+" matched in the class "+obj.getClass().getName());
				e.printStackTrace();
			}
		return result ;
	}
	
    private static <T> void swap(List<T> x, int a, int b) {
    	T t = x.get(a);
    	x.set(a,x.get(b));
    	x.set(b,t);
    }
	
    private static <T,O>  List<int[]> indexOfSameValue(List<T> list,O[] keys){
    	List<int[]> result = new ArrayList<int[]>();
    	T tmp = null ;
    	int i = 0, j = 0 ;
    	for (; i < list.size(); i++) 
			if(tmp==null)
				tmp = list.get(i);
			else {
				if( equalAtKeys(tmp,list.get(i),keys) )
					continue ;
				else{
					result.add( new int[]{j,i-1} );
					j = i ;
					tmp = list.get(i);
				}
			}
    	if(i>0)
    		result.add( new int[]{j,i-1} );
    	return result;
    }
    
    private static <T,O> boolean equalAtKeys(T t1, T t2,O[] keys) {
    	for (O key : keys) 
    		if(valueAt(t1,key) !=null && !  valueAt(t1,key) .equals( valueAt(t2,key) )) 
    			return false;
    	return true ;
    }
    
    private static <T> void listcopy(List<T> src , int srcPos, List<T> dest , int destPos, int length){
    	for (int i = 0 ; i < length ; i++) 
    		dest.set(destPos+i, src.get(srcPos+i));
    }
    
    @SuppressWarnings("unchecked")
	public static <T> T[] copyOfRange(T[] original, int from, int to) {
        return copyOfRange(original, from, to, (Class<T[]>) original.getClass());
    }
    
    @SuppressWarnings("unchecked")
	public static <T,U> T[] copyOfRange(U[] original, int from, int to, Class<? extends T[]> newType) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        T[] copy = ((Object)newType == (Object)Object[].class)
            ? (T[]) new Object[newLength]
            : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, from, copy, 0,
                         Math.min(original.length - from, newLength));
        return copy;
    }
    
    public static List addArray(List list,Object[] array){
    	if(array==null)
    		return list;
    	for(Object obj : array){
    		list.add(obj);
    	}
    	return list;
    }
    
    public static List addObject(List list,Object object){
    	if(object==null)
    		return list;
    	list.add(object);
    	return list;
    }
    
    
	public static void main(String[] args)  {

//		String[] s3 = new String[]{"9","6","8","7","6","5","2","3","3","1"};
//		String[] s2 = new String[]{"8","9","8","7","8","5","4","3","4","4"};
//		String[] s1 = new String[]{"8","8","2","8","8","1","8","1","8","8"};
//		String[] keys =new String[]{  "key2","key1","key3"};
//		List<Map<String,String>> l = new ArrayList<Map<String,String>>();
//		for (int i = 0; i < s1.length; i++) {
//			Map<String,String> m = new HashMap<String,String>();
//			m.put(keys[0],s1[i]);
//			m.put(keys[1],s2[i]);
//			m.put(keys[2],s3[i]);
//			l.add(m);
//		}
//		
//		sortByKey(l,keys);
//		for (Map m : l) {
//			System.out.println(m);
//		}
		
		Integer[] ay = new Integer[100000];
		List<Map> list = new LinkedList<Map>();
		Map m ;
		for (int i = ay.length-1, j=0 ; i >=0; i--,j++) {
			ay[i] = j ;
			m = new HashMap();
			m.put("a",j);
			list.add(m);
		}
		System.out.println(System.currentTimeMillis());
		Arrays.sort(ay);
		System.out.println(System.currentTimeMillis());
		sortByKey(list,"a");
		System.out.println(System.currentTimeMillis());
		
	}
	
}
