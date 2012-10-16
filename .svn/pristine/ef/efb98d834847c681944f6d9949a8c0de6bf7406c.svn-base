package com.ubs.gtp.data.resource.domain;

import junit.framework.Assert;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Tests integration with data-domain module.
 */
@ContextConfiguration(locations = {"classpath:data-application-config.xml"})
@TransactionConfiguration(transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseConnectionTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void databaseConnectionTest() {
        Assert.assertNotNull(sessionFactory);
//        final Query q = sessionFactory.getCurrentSession().createQuery("FROM StockDto WHERE id = :id").setParameter("id", 1);
//        Assert.assertEquals(1, q.list().size());
    }
}
