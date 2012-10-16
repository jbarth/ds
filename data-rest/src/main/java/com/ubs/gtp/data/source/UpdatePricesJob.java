package com.ubs.gtp.data.source;

import com.google.common.base.Optional;
import com.ubs.gtp.data.domain.client.StockRicDto;
import com.ubs.gtp.data.domain.dao.StockRicDao;
import com.ubs.gtp.data.domain.instruments.StockDAO;
import com.ubs.gtp.data.domain.instruments.StockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Updates prices using {@link PriceService}.
 *
 * @author Jakub D Kozlowski
 * @since 0.4
 */
@Component
public class UpdatePricesJob {

    private static final Logger LOG = Logger.getLogger(UpdatePricesJob.class.getName());

    @Autowired
    private PriceService prices;

    @Autowired
    private StockDAO stocks;

    @Autowired
    private StockRicDao ricsDao;

    @Transactional
    @Scheduled(fixedDelay = 10000)
    public void updatePrices() {
        try {
            LOG.log(Level.INFO, "Start updating");

            final List<StockRicDto> rics = ricsDao.getAll();
            if (null == rics || rics.isEmpty()) {
                LOG.log(Level.INFO, "No Rics");
                return;
            }

            final Optional<List<StockDto>> results = prices.getStockDtos(rics);
            if (results.isPresent()) {
                LOG.log(Level.INFO, "Got results");
                for (final StockDto stockDto : results.get()) {
                    final Optional<StockDto> o = stocks.getByRic(stockDto.getRic());

                    try {
                        if (null == stockDto.getTimestamp() ||
                                null == stockDto.getName() ||
                                null == stockDto.getPrice() /*||
                                null == stockDto.getOpeningprice() ||
                                null == stockDto.getEps() ||
                                null == stockDto.getYearlow() ||
                                null == stockDto.getYearhigh()*/) {
                            LOG.log(Level.INFO, "Some fields missing in: " + stockDto);
                        } else {

                            if (o.isPresent()) {
                                LOG.log(Level.INFO, "Found existing ric");

                                final StockDto update = o.get();

                                update.setTimestamp(Calendar.getInstance());
                                update.setPrice(stockDto.getPrice());
                                update.setOpeningprice(stockDto.getOpeningprice());
                                update.setEps(stockDto.getEps());
                                update.setYearlow(stockDto.getYearlow());
                                update.setYearhigh(stockDto.getYearhigh());
                                stocks.update(update);
                            } else {
                                stocks.create(stockDto);
                                LOG.log(Level.INFO, "Didn't find ric");
                            }
                        }
                    } catch (Throwable e) {
                        LOG.log(Level.INFO, "Problem updating: " + stockDto);
                    }
                }
            } else {
                LOG.log(Level.INFO, "No results");
            }
        } catch (Throwable e) {
            LOG.log(Level.INFO, "Ooops", e);
        }
    }
}
