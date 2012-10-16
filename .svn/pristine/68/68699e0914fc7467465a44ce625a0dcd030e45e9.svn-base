package com.ubs.gtp.data.resource.domain;

import com.ubs.gtp.data.domain.client.ClientDto;
import com.ubs.gtp.data.domain.client.PortfolioDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:data-application-config.xml"})
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class PortfolioDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

//    @Autowired
//    protected PortfolioDAO portfolioDao;

    private PortfolioDto portfolioDto;

    @Before
    public void setup() {
        portfolioDto = new PortfolioDto();
//        portfolioDto.setClient(new ClientDto());
        portfolioDto.setReal(true);
//        portfolioDto.setStartdate(new Date());
    }

    @Test
    public void shouldInsertIntoDatabase() {

//        portfolioDao.create(portfolioDto);
//        PortfolioDto retrievedPortfolio = portfolioDao.getById(portfolioDto.getPortfolioId());
//        assertNotNull(retrievedPortfolio);
    }

    @Test
    public void shouldDeleteFromPortfolioTableByID() {
//        portfolioDao.create(portfolioDto);
//        portfolioDao.deleteById(portfolioDto.getPortfolioId());
//        assertNull(portfolioDao.getById(portfolioDto.getPortfolioId()));
    }
}
