package com.ubs.gtp.data.resource.trade;

import java.math.BigDecimal;

/**
 * Dto representing an incoming trade (same for both buy or sell)
 *
 * @author Mihkel Aamer
 * @since 0.1
 */
public final class OrderDto {

	private String side;
    private int clientID;
    private int portfolioID;
    private String stockID;
    private BigDecimal volume;
    private boolean isCA;

    public OrderDto() {
    }

    public OrderDto(final String side, final int clientID, final int portfolioID, final String stockID, final BigDecimal volume, final boolean isCA) {
    	this.side = side;
    	this.portfolioID = portfolioID;
        this.clientID = clientID;
        this.stockID = stockID;
        this.volume = volume;
        this.isCA = true;
    }
    
    
    public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getPortfolioID() {
		return portfolioID;
	}

	public void setPortfolioID(int portfolioID) {
		this.portfolioID = portfolioID;
	}
	
    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

	public boolean getIsCA() {
		return isCA;
	}

	public void setIsCA(boolean isCA) {
		this.isCA = isCA;
	}
}
