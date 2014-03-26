package com.sands.sys.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

public class ClassUtils {
	
	
	@SuppressWarnings( "rawtypes" )
	public static List<Class> getTopLevelClasses( String packagename ) throws IOException{
		ClassLoader classloader = ClassLoader.getSystemClassLoader() ;
		ClassPath classPath =  ClassPath.from( classloader );
		ImmutableSet<ClassInfo> classes = classPath.getTopLevelClasses( packagename);
		List<Class> clz = new ArrayList<Class>();
		for (ClassInfo classInfo : classes) {
			clz.add(  classInfo.load() ) ;
		}
		return clz;
	}

	@SuppressWarnings( "rawtypes" )
	public static Class[] getClassesIn( String packagename ) throws IOException{
		return getTopLevelClasses( packagename ).toArray( new Class[0]);
	}
}
