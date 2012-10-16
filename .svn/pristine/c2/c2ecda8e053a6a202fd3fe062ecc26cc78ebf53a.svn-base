package com.ubs.gtp.data.domain.client;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Jakub.Kozlowski
 * Date: 09/10/12
 * Time: 15:20
 * To change this template use File | Settings | File Templates.
 */
@Embeddable
public class PortfolioItemDtoKey implements Serializable {

    private static final long serialVersionUID = 3355234269423003948L;

    @Column(name = "STOCK_ID", nullable = false, insertable = false, updatable = false)
    private Integer stockId;

    @Column(name = "PORTFOLIO_ID", nullable = false, insertable = false, updatable = false)
    private Integer portfolioId;

    /**
     * Empty constructor.
     */
    public PortfolioItemDtoKey() {
    }

    public Integer getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Integer portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }
}
