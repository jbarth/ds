package com.ubs.gtp.data.resource.news;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ubs.gtp.data.domain.NewsDto;
import com.ubs.gtp.data.domain.dao.NewsDao;
import com.ubs.gtp.data.domain.instruments.StockDAO;


/**
 * Controller for getting news through API.
 *
 * @author Mihkel Aamer
 * @since 0.4
 */

@Controller
@Transactional
@Repository
public class NewsController {
	
	@Autowired
	private NewsDao news;
	
	@Autowired
	private StockDAO stocks;
	
	@ResponseBody
    @RequestMapping(value = "/news/{ric}", method = RequestMethod.GET)
	public ModelAndView getNews(final @PathVariable String ric, final HttpServletResponse response) {
		// Get the stockid		
		if (!stocks.getByRic(ric).isPresent())
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ModelAndView();			
		}		
		if (!news.getAllById((stocks.getByRic(ric).get().getId())).isPresent())
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ModelAndView();
		}
		List<NewsDto> newsitems = news.getAllById((stocks.getByRic(ric).get().getId())).get();
		for (NewsDto news : newsitems)
		{
			news.setRic(ric);
			news.setTimestamp(news.getId().getTimestamp());
		}
		return new ModelAndView("news", "news", newsitems);
	}
}
