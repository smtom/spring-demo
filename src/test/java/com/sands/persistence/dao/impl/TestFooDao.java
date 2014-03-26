package com.sands.persistence.dao.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sands.junit.BaseTest;
import com.sands.persistence.dao.impl.FooDao;
import com.sands.persistence.pojo.Foo;


public class TestFooDao extends BaseTest{

	@Autowired FooDao dao;
	
	@Test
	@Transactional
	public void test(){
		
//		Foo foo = new Foo();
//		foo.setName( "sands" );
//		fooDao.create( foo );
		
		List<Foo> foos = dao.findAll();
		for (Foo foo : foos) {
			System.out.println( foo.getName() );
		}
	}
}
