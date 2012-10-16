package com.ubs.gtp.data.resource;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import com.ubs.gtp.data.domain.instruments.StockDAO;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.DispatcherServlet;
import com.ubs.gtp.data.domain.NewsDto;
import com.ubs.gtp.data.domain.NewsDtoKey;
import com.ubs.gtp.data.domain.dao.NewsDao;
import com.ubs.gtp.data.domain.instruments.StockDto;
import com.ubs.gtp.data.resource.news.NewsController;

/**
 * Tests {@link NewsController}.
 *
 * @author Mihkel Aamer
 * @since 0.4
 */

@MockWebApplication(name = "data")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager")
@ContextConfiguration(locations = {"classpath:test-data-servlet.xml"}, loader = MockWebApplicationContextLoader.class)
public class NewsControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private StockDAO stocks;

	@Autowired
    private NewsDao newsdao;
	
	@Autowired
    private DispatcherServlet servlet;
	
	@Test
    public void testGetNews() throws Exception  {
		// Create stock, add news to it and get response
		StockDto stock = new StockDto();
		stock.setRic("ubs");
		stock.setIndustry("Finance");
		stock.setEps(new BigDecimal(2.2));
		stock.setName("UBS");
		stock.setOpeningprice(new BigDecimal(10));
		stock.setPrice(new BigDecimal(12));
		stock.setTimestamp(Calendar.getInstance());
		stock.setYearhigh(new BigDecimal(15));
		stock.setYearlow(new BigDecimal(5));
        stocks.create(stock);

        
        NewsDtoKey key = new NewsDtoKey();
        key.setStockid(stock.getId());
        key.setTimestamp(new Date());
		NewsDto news = new NewsDto();
		news.setId(key);
		news.setHeadline("Something here");
		news.setContents("Testing");
		newsdao.create(news);
		
		final MockHttpServletRequest request = new MockHttpServletRequest(RequestMethod.GET.name(), "/news/ubs");
        final MockHttpServletResponse response = new MockHttpServletResponse();
        servlet.service(request, response);
        final String results = response.getContentAsString().trim();
        Assert.assertTrue(!results.isEmpty());
	}
}
