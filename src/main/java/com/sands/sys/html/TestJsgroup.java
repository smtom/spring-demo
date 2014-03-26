package com.sands.sys.html;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.sands.sys.utils.FileUtil;


public class TestJsgroup {
	
	
	public static void main( String[] args ) throws IOException {
		Document doc = Jsoup.connect("http://www.learnersdictionary.com/definition/doctor").get();
		
		
		//System.out.println( doc.html() );
		//FileUtil.stringToFile( doc.html(), "D:/test.html" );
		Element ele = doc.getElementById( "wordclickDiv" );
		System.out.println(ele.text());
	}

}
