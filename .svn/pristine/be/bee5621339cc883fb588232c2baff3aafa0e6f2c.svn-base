package com.ubs.gtp.data.domain.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;
import com.ubs.gtp.data.domain.AbstractHibernateDAO;
import com.ubs.gtp.data.domain.NewsDto;

/**
 * Manages {@link NewsDto}.
 *
 * @author Mihkel Aamer
 * @since 0.4
 */
@Repository
@Transactional
public class NewsDao extends AbstractHibernateDAO<NewsDto> {
	
	public NewsDao() {
		super(NewsDto.class);		
	}
	
	
	/**
	 * @param stockid Stock id of the stock the news is returned for
	 * @return
	 */
	public Optional<List<NewsDto>> getAllById(final int stockid)
	{
		
		Criteria cr = getCurrentSession().createCriteria(NewsDto.class);
		cr.add(Restrictions.eq("id.stockid", stockid));
		List<NewsDto> results = cr.list();
        return Optional.fromNullable(results);
	}

}
