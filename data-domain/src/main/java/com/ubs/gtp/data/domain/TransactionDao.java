package com.ubs.gtp.data.domain;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




/**
 * Manages {@link TransactionDto}.
 *
 * @author Mihkel Aamer
 * @since 0.3
 */
@Repository
@Transactional
public class TransactionDao extends AbstractHibernateDAO<TransactionDto> {

    public TransactionDao() {
        super(TransactionDto.class);
    }
    
    
    public List<TransactionDto> getByPortfolioId(final int portfolioId)
    {
    	Property pid = Property.forName("portfolioid");
    	Criteria q = getCurrentSession().createCriteria((TransactionDto.class));    	
    	q.add(pid.eq(portfolioId));
    	return q.list();
    }
    
    
}