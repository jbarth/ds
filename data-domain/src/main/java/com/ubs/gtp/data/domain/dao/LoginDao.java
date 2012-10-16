package com.ubs.gtp.data.domain.dao;

import com.ubs.gtp.data.domain.AbstractHibernateDAO;
import com.ubs.gtp.data.domain.client.LoginDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manages {@link LoginDto}.
 *
 * @author Jakub D Kozlowski
 * @since 0.3
 */
@Repository
@Transactional
public class LoginDao extends AbstractHibernateDAO<LoginDto> {

    public LoginDao() {
        super(LoginDto.class);
    }
}

