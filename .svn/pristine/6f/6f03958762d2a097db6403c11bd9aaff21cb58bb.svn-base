package com.ubs.gtp.data.domain.dao;

import com.google.common.base.Optional;
import com.ubs.gtp.data.domain.AbstractHibernateDAO;
import com.ubs.gtp.data.domain.client.ClientDto;
import com.ubs.gtp.data.domain.client.StockHistoryDto;
import com.ubs.gtp.data.domain.instruments.StockDAO;
import com.ubs.gtp.data.domain.instruments.StockDto;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Manages {@link StockHistoryDto}.
 *
 * @author Jakub D Kozlowski
 * @since 0.3
 */
@Repository
@Transactional
public class StockHistoryDao extends AbstractHibernateDAO<StockHistoryDto> {

    private JdbcTemplate template;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private StockDAO stocks;

    public StockHistoryDao() {
        super(StockHistoryDto.class);
    }

    /**
     * Gets all {@link StockHistoryDto}s for this {@code ric}.
     *
     * @param ric stock to get the {@link StockHistoryDto}s for..
     * @return all {@link StockHistoryDto}s for this {@code ric}.
     * @throws NullPointerException     if {@code ric} is null.
     * @throws IllegalArgumentException if {@code maxResults <= 0}.
     */
    public Optional<List<StockHistoryDto>> getByRic(final String ric, final Integer maxResults) {

        checkNotNull(ric);
        checkArgument(maxResults > 0);

        final Optional<StockDto> stockDto = stocks.getByRic(ric);
        if (!stockDto.isPresent()) {
            return Optional.absent();
        }

        final String sql = "SELECT * FROM STOCK_HISTORY sh WHERE sh.STOCK_ID=(SELECT s.STOCK_ID FROM STOCK_INFO s WHERE lower(s.RIC)=lower(?)) ORDER BY sh.DAY_DATE DESC";

        final RowMapper<StockHistoryDto> mapper = new RowMapper<StockHistoryDto>() {
            public StockHistoryDto mapRow(final ResultSet resultSet, final int i) throws SQLException {
                final StockHistoryDto stockHistory = new StockHistoryDto();
                stockHistory.setDate(resultSet.getTimestamp("DAY_DATE"));
                stockHistory.setDailymax(resultSet.getBigDecimal("DAILY_MAX"));
                stockHistory.setDailymin(resultSet.getBigDecimal("DAILY_MIN"));
                stockHistory.setEodprice(resultSet.getBigDecimal("EOD_PRICE"));
                stockHistory.setOpeningprice(resultSet.getBigDecimal("OPENING_PRICE"));
                return stockHistory;
            }
        };

        final JdbcTemplate template = new JdbcTemplate(dataSource);
        template.setMaxRows(maxResults);

        final List<StockHistoryDto> history = template.query(sql, new Object[]{ric}, mapper);
        return Optional.fromNullable(history);
    }
}
