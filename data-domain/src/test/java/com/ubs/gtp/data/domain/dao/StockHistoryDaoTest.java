package com.ubs.gtp.data.domain.dao;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Tests {@link StockHistoryDao}.
 *
 * @author Jakub D Kozlowski
 * @since 0.3
 */
@ContextConfiguration(locations = {"classpath:data-application-config.xml"})
@TransactionConfiguration(transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
public class StockHistoryDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private StockHistoryDao stockHistory;

    @Test
    public void testGetAll() {
        Assert.assertNotNull(stockHistory);
//        Assert.assertTrue(!stockHistory.getAll().isEmpty());
    }
}