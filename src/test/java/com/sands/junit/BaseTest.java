package com.sands.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;



@ContextConfiguration(locations = { "classpath:spring/app-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BaseTest {

//	@Autowired
//	protected WebApplicationContext wac;
//
//	protected MockMvc mockMvc;
//	
//	@Before
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//    }
	
	
	@Test public void test(){
		System.out.println("===============");
	}
	
}
