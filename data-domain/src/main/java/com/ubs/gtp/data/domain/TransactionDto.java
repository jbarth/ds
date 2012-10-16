package com.ubs.gtp.data.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import com.ubs.gtp.data.domain.client.PortfolioDto;
import com.ubs.gtp.data.domain.instruments.StockDto;


/**
 * Entity for {@code TRANSACTION} table in the database.
 *
 * @author Mihkel Aamer
 * @version since 0.3
 */
@Entity
@Table(name = "TRANSACTION")
public class TransactionDto implements Serializable {
	
	private static final long serialVersionUID = 1355234269423003948L;
	
	@Transient
	private String ric;
	
	@Id
	@JsonIgnore
	@GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "TRANSACTION_ID")
	private int transactionid;
	
	@JsonIgnore
	@Column(name = "PORTFOLIO_ID")
	private int portfolioid;
	
	@JsonIgnore
	@Column(name = "STOCK_ID")
	private int stockid;
	
	@Column(name = "QUANTITY")
	private BigDecimal quantity;
	
	@Column(name = "PRICE")
	private BigDecimal price;
	
	@Column(name = "MADE_BY_FA")
	private int madebyfa;
	
	@Column(name = "TRANS_TIME")
	private Date transtime;	

	public TransactionDto()
	{
		
	}
	
	public String getRic() {
		return ric;
	}

	public void setRic(String ric) {
		this.ric = ric;
	}
	
	public int getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}
	public int getPortfolioid() {
		return portfolioid;
	}
	public void setPortfolioid(int portfolioid) {
		this.portfolioid = portfolioid;
	}
	public int getStockid() {
		return stockid;
	}
	public void setStockid(int stockid) {
		this.stockid = stockid;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getMadebyfa() {
		return madebyfa;
	}
	public void setMadebyfa(int madebyfa) {
		this.madebyfa = madebyfa;
	}
	public Date getTranstime() {
		return transtime;
	}
	public void setTranstime(Date transtime) {
		this.transtime = transtime;
	}

}
