package com.ubs.gtp.data.domain.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ubs.gtp.data.domain.AbstractHibernateDAO;
import com.ubs.gtp.data.domain.client.PortfolioDto;


/**
 * Manages {@link PortfolioDto}.
 *
 * @author Mihkel Aamer
 * @since 0.3
 */
@Repository
@Transactional
public class PortfolioDao extends AbstractHibernateDAO<PortfolioDto> {

    public PortfolioDao() {
        super(PortfolioDto.class);
    }
}