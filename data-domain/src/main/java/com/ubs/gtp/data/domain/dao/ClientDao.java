package com.ubs.gtp.data.domain.dao;

import com.ubs.gtp.data.domain.AbstractHibernateDAO;
import com.ubs.gtp.data.domain.client.ClientDto;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manages {@link ClientDto}.
 * 
 * @author Jakub D Kozlowski
 * @since 0.3
 */
@Repository
@Transactional
public class ClientDao extends AbstractHibernateDAO<ClientDto> {

	public ClientDao() {
		super(ClientDto.class);
	}

}
