package com.sands.persistence.dao.impl;


import org.springframework.stereotype.Repository;

import com.sands.persistence.dao.IFooDao;
import com.sands.persistence.dao.common.AbstractHibernateDao;
import com.sands.persistence.pojo.Foo;

@Repository
public class FooDao extends AbstractHibernateDao<Foo> implements IFooDao {

    public FooDao() {
        super();

        setClazz(Foo.class);
    }

    // API

}
