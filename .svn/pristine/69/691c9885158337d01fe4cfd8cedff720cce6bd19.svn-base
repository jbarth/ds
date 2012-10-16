package com.ubs.gtp.data.resource.trade;

import com.google.common.base.Optional;
import com.ubs.gtp.data.domain.client.ClientDto;
import com.ubs.gtp.data.domain.client.PortfolioItemDto;
import com.ubs.gtp.data.domain.instruments.StockDto;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ubs.gtp.data.domain.TransactionDao;
import com.ubs.gtp.data.domain.TransactionDto;
import com.ubs.gtp.data.domain.client.PortfolioDto;
import com.ubs.gtp.data.domain.dao.ClientDao;
import com.ubs.gtp.data.domain.dao.PortfolioDao;
import com.ubs.gtp.data.domain.instruments.StockDAO;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller for the trade gateway which executes buy and sell trades and
 * retrieves list of transactions.
 * 
 * @author Mihkel Aamer
 * @since 0.3
 */
@Controller
@Transactional
@Repository
public class OrderController {

	@Autowired
	private TransactionDao transactions;

	@Autowired
	private StockDAO stocks;

	@Autowired
	private ClientDao clients;

	@Autowired
	private PortfolioDao portfolios;

	@Autowired
	private SessionFactory sessionFactory;

	@ResponseBody
	@RequestMapping(value = "/trade/transactions/{portfolioId}", method = RequestMethod.GET)
	public ModelAndView getTransactions(final @PathVariable int portfolioId) {
		List<TransactionDto> trxs = transactions.getByPortfolioId(portfolioId);
		for (int i = 0; i < trxs.size(); i++) {
			final Optional<StockDto> stock = stocks.getById(trxs.get(i)
					.getStockid());
			trxs.get(i).setRic(stock.get().getRic());
		}
		return new ModelAndView("transactions", "transactions", trxs);
	}

	@ResponseBody
	@RequestMapping(value = "/trade", method = RequestMethod.POST)
	public ModelAndView executeTrade(final @RequestBody OrderDto order,
			final HttpServletResponse response) {
		final BigDecimal currentprice;
		TransactionDto newtx = new TransactionDto();
		if (order.getSide().toUpperCase().startsWith("BUY")) {
			newtx.setQuantity(order.getVolume());
		} else if (order.getSide().toUpperCase().startsWith("SELL")) {
			BigDecimal sellQuantity = order.getVolume().multiply(new BigDecimal(-1));
			order.setVolume(sellQuantity);
			newtx.setQuantity(sellQuantity);
			
			System.out.println("***QUANTITY ==>> " + order.getVolume() + "***");
			System.out.println("***QUANTITY ==>> " + newtx.getQuantity() + "***");
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return new ModelAndView("trade", "trade", new OrderReplyDto(false,
					null));
		}
		final Optional<StockDto> stockDto = stocks.getByRic(order.getStockID());
		if (!stockDto.isPresent()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return new ModelAndView("trade", "trade", new OrderReplyDto(false,
					null));
		}

		final Optional<ClientDto> client = clients.getById(order.getClientID());
		if (!client.isPresent()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return new ModelAndView("trade", "trade", new OrderReplyDto(false,
					null));
		}
		final Optional<PortfolioDto> portfolio = portfolios.getById(order
				.getPortfolioID());

		// Check if entered portfolio ID belongs to that client
		if (!client.get().getPortfolios().contains(portfolio.get())) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return new ModelAndView("trade", "trade", new OrderReplyDto(false,
					null));
		}
		// Check if the operation is forbidden
		currentprice = stockDto.get().getPrice();
		if (order.getVolume().doubleValue() > 0) {
			if (client.get().getBalance()
					.compareTo(order.getVolume().multiply(currentprice)) == -1) {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return new ModelAndView("trade", "trade", new OrderReplyDto(
						false, null));
			}
		} else if (order.getVolume().doubleValue() < 0) {
			BigDecimal currentqty = new BigDecimal(0);
			PortfolioItemDto[] stocksinportfolio = portfolios
					.getById(order.getPortfolioID()).get().getItems()
					.toArray(new PortfolioItemDto[0]);
			for (int i = 0; i < stocksinportfolio.length; i++) {
				if (stocksinportfolio[i].getStock().getRic()
						.equalsIgnoreCase(order.getStockID())) {
					currentqty = new BigDecimal(
							stocksinportfolio[i].getVolume());
				}
			}
			if (currentqty.compareTo(order.getVolume().abs()) == -1) {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return new ModelAndView("trade", "trade", new OrderReplyDto(
						false, null));
			}

		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return new ModelAndView("trade", "trade", new OrderReplyDto(false,
					null));
		}
		newtx.setPortfolioid(portfolios.getById(order.getPortfolioID()).get()
				.getPortfolioId());
		newtx.setPrice(currentprice);
		newtx.setStockid(stockDto.get().getId());
		newtx.setTranstime(new Date());
		if (order.getIsCA())
			newtx.setMadebyfa(1);
		else
			newtx.setMadebyfa(0);
		
		System.out.println("***QUANTITY ==>> " + newtx.getQuantity() + "***");
		
		transactions.create(newtx);
		sessionFactory.getCurrentSession().flush();
		return new ModelAndView("trade", "trade", new OrderReplyDto(true,
				currentprice));
	}
}
