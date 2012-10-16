package com.ubs.gtp.data.source;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.ubs.gtp.data.domain.client.StockRicDto;
import com.ubs.gtp.data.domain.instruments.StockDto;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Default implementation of {@link PriceService}
 * that retrieves prices from {@code http://uk.finance.yahoo.com/}.
 * <p/>
 * <p>{@link YahooPriceService} manually adds {@code .L} at the end of the rics
 * in order to retrieve correct prices, and returns them back without that.</p>
 *
 * @author Jakub D Kozlowski
 * @since 0.4
 */
@Service
public final class YahooPriceService implements PriceService {

    private static final Logger LOG = Logger.getLogger(YahooPriceService.class.getName());

    private static final String YAHOO_QUERY = "q=select * from yahoo.finance.quotes where symbol in (%s)&format=json&diagnostics=true&env=store://datatables.org/alltableswithkeys";

    /**
     * {@inheritDoc}
     */
    public Optional<List<StockDto>> getStockDtos(final List<StockRicDto> rics) {
        try {
            checkNotNull(rics);
            checkArgument(!rics.isEmpty());

            final String query = String.format(YAHOO_QUERY, wrappedJoin(rics, ","));
            final URI url = new URI("http", "query.yahooapis.com", "/v1/public/yql", query, null);

            LOG.log(Level.INFO, url.toString());

            final ObjectMapper m = new ObjectMapper();
            final JsonNode quotesNode = m.readValue(url.toURL(), JsonNode.class).get("query").get("results").get("quote");
            final List<YahooQuote> quotes = m.readValue(quotesNode, new TypeReference<List<YahooQuote>>() {
            });

            final List<StockDto> stockDtos = Lists.newArrayList();
            for (final YahooQuote q : quotes) {
                final StockDto stockDto = new StockDto();
                /**
                 * I manually drop {@code .L} at the end of the ric;
                 * not very robust, but it will have to do for now.
                 */
                stockDto.setRic(q.getRic().split("\\.")[0]);
                stockDto.setTimestamp(Calendar.getInstance());
                stockDto.setName(q.getName());
                stockDto.setPrice(q.getBid());
                stockDto.setOpeningprice(q.getOpeningPrice());
                stockDto.setEps(q.getEps());
                stockDto.setYearlow(q.getYearLow());
                stockDto.setYearhigh(q.getYearHigh());
                stockDtos.add(stockDto);
            }

            return Optional.fromNullable(stockDtos);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Could not get prices", e);
            return Optional.absent();
        } catch (URISyntaxException e) {
            LOG.log(Level.SEVERE, "Could not get prices", e);
            return Optional.absent();
        }
    }

    /**
     * Gets {@link StockRicDto#getRic()} wrapped in {@code ""} and joins them with {@code separator}.
     *
     * @param rics      rics to wrap and join.
     * @param separator separator for the join.
     * @return joined {@link StockRicDto#getRic()}.
     */
    private String wrappedJoin(final Iterable<StockRicDto> rics, final String separator) {
        checkNotNull(rics);
        checkNotNull(separator);

        final Iterable<String> wrappedRics = Iterables.transform(rics, new Function<StockRicDto, String>() {
            public String apply(final StockRicDto ric) {
                checkNotNull(ric);
                checkNotNull(ric.getRic());
                checkArgument(!ric.getRic().isEmpty());
                /**
                 * I manually add {@code .L} at the end of the ric;
                 * not very robust, but it will have to do for now.
                 */
                return "\"" + ric.getRic() + ".L" + "\"";
            }
        });
        return Joiner.on(separator).join(wrappedRics);
    }
}
