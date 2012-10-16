package com.ubs.gtp.data.domain.dao;

import com.ubs.gtp.data.domain.instruments.StockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Example spring-jdbc setup.
 *
 * @author Jakub D Kozlowski
 * @since 0.3
 */
@Repository
@Transactional
public class JdbcDao {

    private JdbcTemplate template;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public StockDto findStock(final Integer id) {

        final String sql = "select STOCK_ID, RIC from STOCK_INFO" + " where STOCK_ID=?";

        final RowMapper<StockDto> mapper = new RowMapper<StockDto>() {
            public StockDto mapRow(final ResultSet resultSet, final int i) throws SQLException {
                final StockDto stock = new StockDto();
                stock.setId(resultSet.getInt("STOCK_ID"));
                stock.setRic(resultSet.getString("RIC"));
                return stock;
            }
        };

        return (StockDto) template.queryForObject(sql, new Object[]{id}, mapper);
    }
}
