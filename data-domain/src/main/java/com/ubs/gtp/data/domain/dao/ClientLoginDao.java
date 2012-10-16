package com.ubs.gtp.data.domain.dao;

import com.ubs.gtp.data.domain.AbstractHibernateDAO;
import com.ubs.gtp.data.domain.client.ClientLoginDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manages {@link ClientLoginDto}.
 *
 * @author Jakub D Kozlowski
 * @since 0.3
 */
@Repository
@Transactional
public class ClientLoginDao extends AbstractHibernateDAO<ClientLoginDto> {

    public ClientLoginDao() {
        super(ClientLoginDto.class);
    }
}

