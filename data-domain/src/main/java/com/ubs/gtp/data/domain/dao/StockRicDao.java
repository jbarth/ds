package com.ubs.gtp.data.domain.dao;

import com.ubs.gtp.data.domain.AbstractHibernateDAO;
import com.ubs.gtp.data.domain.client.StockHistoryDto;
import com.ubs.gtp.data.domain.client.StockRicDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Transactional
public class StockRicDao extends AbstractHibernateDAO<StockRicDto> {

    public StockRicDao() {
        super(StockRicDto.class);
    }
}
