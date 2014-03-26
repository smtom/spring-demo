package com.sands.persistence.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sands.persistence.dao.IFooDao;
import com.sands.persistence.dao.common.IOperations;
import com.sands.persistence.dao.impl.FooDao;
import com.sands.persistence.pojo.Foo;
import com.sands.persistence.service.IFooService;
import com.sands.persistence.service.common.AbstractService;


@Service
public class FooService extends AbstractService<Foo> implements IFooService
{

    @Autowired private FooDao dao;

    public FooService() {
        super();
    }

    // API

    @Override
    protected IOperations<Foo> getDao() {
        return dao;
    }
    
//	public List<Foo> findAll(){
//		return dao.findAll();
//	}

}
