package com.sands.persistence.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sands.junit.BaseTest;
import com.sands.persistence.pojo.Foo;

public class TestFooService extends BaseTest {

	@Autowired private FooService fooService;

	@Test
	public void get(){
		List<Foo> foos = fooService.findAll();
		for (Foo foo : foos) {
			System.out.println( foo.getName() );
		}
		
	}
	
}
