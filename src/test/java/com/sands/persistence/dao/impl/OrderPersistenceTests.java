package com.sands.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.classic.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sands.junit.BaseTest;
import com.sands.persistence.dao.impl.FooDao;
import com.sands.persistence.pojo.Foo;
import com.sands.persistence.pojo.Item;
import com.sands.persistence.pojo.Order;

public class OrderPersistenceTests extends BaseTest{

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired private FooDao fooDao;
	
	@Test
	@Transactional
	public void test(){
		
		Order order = new Order();
		order.setCustomer( "yyyy" );
		Session session = sessionFactory.getCurrentSession();
		session.save(order);
		session.flush();
		
//		Face order = new Face();
//		order.setCustomer( "yyyy" );
//		Session session = sessionFactory.getCurrentSession();
//		session.save(order);
//		session.flush();
		
		
//		Foo order = new Foo();
//		order.setName( "yyyy" );
//		Session session = sessionFactory.getCurrentSession();
//		session.save(order);
//		session.flush();
		
		
	}
	
	
	
//	@Test
//	@Transactional
	public void testSaveOrderWithItems() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Order order = new Order();
		order.getItems().add(new Item());
		session.save(order);
		session.flush();
		assertNotNull(order.getId());
	}

//	@Test
//	@Transactional
	public void testSaveAndGet() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Order order = new Order();
		order.getItems().add(new Item());
		session.save(order);
		session.flush();
		// Otherwise the query returns the existing order (and we didn't set the
		// parent in the item)...
		session.clear();
		Order other = (Order) session.get(Order.class, order.getId());
		assertEquals(1, other.getItems().size());
		assertEquals(other, other.getItems().iterator().next().getOrder());
	}

//	@Test
//	@Transactional
	public void testSaveAndFind() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Order order = new Order();
		Item item = new Item();
		item.setProduct("foo");
		order.getItems().add(item);
		session.save(order);
		session.flush();
		// Otherwise the query returns the existing order (and we didn't set the
		// parent in the item)...
		session.clear();
		Order other = (Order) session
				.createQuery( "select o from Order o join o.items i where i.product=:product")
				.setString("product", "foo").uniqueResult();
		assertEquals(1, other.getItems().size());
		assertEquals(other, other.getItems().iterator().next().getOrder());
		System.out.println( ">>>>>>>>>>>>>>" + order.getItems());
	}

}
