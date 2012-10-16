package com.ubs.gtp.data.domain.instruments;

import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager")
@ContextConfiguration(locations = {"classpath:data-application-config.xml"})
public class StockDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    protected StockDAO stockDao;

    private StockDto stockDTO;

    @Before
    public void setup() {
        BigDecimal price = new BigDecimal(653.50);
        stockDTO = new StockDto("test", price, "test", Calendar.getInstance());
    }

    @Test
    public void shouldInsertIntoDatabase() {
        stockDao.create(stockDTO);
        final Optional<StockDto> retrievedStock = stockDao.getById(stockDTO.getId());
        assertTrue(retrievedStock.isPresent());
    }

    @Test
    public void shouldUpdateStockName() {
        stockDao.create(stockDTO);
        final Optional<StockDto> retrievedStock = stockDao.getById(stockDTO.getId());
        assertTrue(retrievedStock.isPresent());
        retrievedStock.get().setName("this was google");
        stockDao.update(retrievedStock.get());

        final Optional<StockDto> updatedStock = stockDao.getById(retrievedStock.get().getId());
        assertTrue(updatedStock.isPresent());
        assertEquals(updatedStock.get().getName(), "this was google");
    }

    @Test
    public void shouldDeleteFromStockTableByID() {
        stockDao.create(stockDTO);
        stockDao.deleteById(stockDTO.getId());
        assertFalse(stockDao.getById(stockDTO.getId()).isPresent());
    }
}
