package com.ubs.gtp.data.resource.history;

import com.google.common.base.Optional;
import com.ubs.gtp.data.domain.client.StockHistoryDto;
import com.ubs.gtp.data.domain.dao.StockHistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Controller for the {@code /history} resource.
 *
 * @author Jakub D Kozlowski
 * @since 0.3
 */
@Controller
@Transactional
@Repository
public class HistoryController {

    @Autowired
    private StockHistoryDao stockHistory;

    /**
     * Gets the historical prices.
     *
     * @param ric  ric to get the historical prices for.
     * @param days how many days of prices to get.
     * @return historical prices for this {@code ric} for {@code days} in the past.
     */
    @RequestMapping(value = "/history/{ric}", method = RequestMethod.GET)
    public ModelAndView getHistoricalStocks(final @PathVariable String ric,
                                            final @RequestParam(value = "days", required = true) Integer days,
                                            final HttpServletResponse response) {

        final Optional<List<StockHistoryDto>> history = stockHistory.getByRic(ric, days);
        if (!history.isPresent()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ModelAndView();
        } else {
            return new ModelAndView("history", "history", history.get());
        }
    }
}
