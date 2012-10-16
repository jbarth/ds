package com.ubs.gtp.data.domain.dao;

import com.google.common.base.Optional;
import com.ubs.gtp.data.domain.AbstractHibernateDAO;
import com.ubs.gtp.data.domain.client.AdvisorDto;
import com.ubs.gtp.data.domain.client.ClientDto;
import com.ubs.gtp.data.domain.instruments.StockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Manages {@link AdvisorDto}.
 *
 * @author Joshua Barth
 * @since 0.3
 */
@Repository
@Transactional
public class AdvisorDao extends AbstractHibernateDAO<AdvisorDto> {

    private JdbcTemplate template;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    /**
     * Instantiates a new stocks dao.
     */
    public AdvisorDao() {
        super(AdvisorDto.class);
    }

    /**
     * Gets the clients of for this {@code advisorId}.
     *
     * @param advisorId id of the advisor to get the clients for.
     * @return the clients for this {@code advisorId}, or {@link Optional#absent()} if advisor does not exist.
     */
    public Optional<List<ClientDto>> getClientsByAdvisorId(final Integer advisorId) {

        final Optional<AdvisorDto> advisor = getById(advisorId);
        if (!advisor.isPresent()) {
            return Optional.absent();
        }

        final String sql = "select * from CLIENT where ADVISOR_ID=?";

        final RowMapper<ClientDto> mapper = new RowMapper<ClientDto>() {
            public ClientDto mapRow(final ResultSet resultSet, final int i) throws SQLException {
                final ClientDto client = new ClientDto();
                client.setClientId(resultSet.getInt("CLIENT_ID"));
                client.setName(resultSet.getString("CLIENT_NAME"));
                client.setName(resultSet.getString("CLIENT_SURNAME"));
                client.setIsFirstLogin(resultSet.getBoolean("IS_FIRST_LOGIN"));
                client.setBalance(resultSet.getBigDecimal("BALANCE"));
                return client;
            }
        };

        final List<ClientDto> clients = template.query(sql, new Object[]{advisorId}, mapper);
        return Optional.of(clients);
    }
    
    /**
     * Gets the clients ids of for this {@code advisorId}.
     *
     * @param advisorId id of the advisor to get the clients for.
     * @return the clients for this {@code advisorId}, or {@link Optional#absent()} if advisor does not exist.
     */
    public Optional<List<Integer>> getClientIdsByAdvisorId(final Integer advisorId) {

        final Optional<AdvisorDto> advisor = getById(advisorId);
        if (!advisor.isPresent()) {
            return Optional.absent();
        }

        final String sql = "select CLIENT_ID from CLIENT where ADVISOR_ID=?";

        final RowMapper<Integer> mapper = new RowMapper<Integer>() {
            public Integer mapRow(final ResultSet resultSet, final int i) throws SQLException {
                final Integer clientId = resultSet.getInt("CLIENT_ID");
                return clientId;
            }
        };

        final List<Integer> clientIds = template.query(sql, new Object[]{advisorId}, mapper);
        return Optional.of(clientIds);
    }
    
    /**
     * Gets the advisor by username.
     *
     * @param advisorId id of the advisor to get the clients for.
     * @return the clients for this {@code advisorId}, or {@link Optional#absent()} if advisor does not exist.
     */
    public Optional<AdvisorDto> getBySamAccountName(final String samAccountName) {

        final String sql = "select * from ADVISOR where SAMACCOUNTNAME = ?";

        final RowMapper<AdvisorDto> mapper = new RowMapper<AdvisorDto>() {
            public AdvisorDto mapRow(final ResultSet resultSet, final int i) throws SQLException {
            	final AdvisorDto advisor = new AdvisorDto();
            	advisor.setSamAccountName(resultSet.getString("SAMACCOUNTNAME"));
            	advisor.setAdvisorId(resultSet.getInt("ADVISOR_ID"));
                return advisor;
            }
        };

        final AdvisorDto advisorDto = template.queryForObject(sql, new Object[]{samAccountName}, mapper);
        return Optional.of(advisorDto);
    }
}
