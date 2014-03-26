package com.sands.persistence.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.annotations.common.util.ReflectHelper;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.hibernate3.HibernateTransactionManager;
//import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
//import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;


import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.sands.persistence.pojo.*;
import com.sands.sys.utils.ClassUtils;

@Configuration
public class HibernateConfiguration {

	@Value("#{dataSource}")
	private DataSource dataSource;

	/**
	 * hibernate 3
	 * @return
	 * @throws IOException 
	 */
//	@Bean
//	public AnnotationSessionFactoryBean sessionFactoryBean() {
//		Properties props = new Properties();
//		props.put("hibernate.dialect", MySQL5Dialect.class.getName());
//		props.put("hibernate.format_sql", "true");
//		props.put("hibernate.show_sql", "true");
//
//		AnnotationSessionFactoryBean bean = new AnnotationSessionFactoryBean();
//		bean.setAnnotatedClasses(new Class[]{Item.class, Order.class});		
//		bean.setHibernateProperties(props);
//		bean.setDataSource(this.dataSource);
//		bean.setSchemaUpdate(true);
//		return bean;
//	}

	
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() throws IOException {
		Properties props = new Properties();
		props.put("hibernate.dialect", MySQL5Dialect.class.getName());
		props.put("hibernate.format_sql", "true");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update");
		// hibernate 4
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setAnnotatedClasses( ClassUtils.getClassesIn( "com.sands.persistence.pojo"  ));
		bean.setHibernateProperties(props);
		bean.setDataSource(this.dataSource);
		
		return bean;
	}

	
	
	@Bean
	public HibernateTransactionManager transactionManager() throws IOException {
		return new HibernateTransactionManager( sessionFactoryBean().getObject() );
	}
	

}
