package com.ubs.gtp.data.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * Entity to represent primary key for {@code STOCK_NEWS} table in the database.
 *
 * @author Mihkel Aamer
 * @since 0.4
 */
public class NewsDtoKey implements Serializable {
	
	private static final long serialVersionUID = 1355933269423003948L;
	
	@JsonIgnore
	@Column(name = "STOCK_ID", nullable = false)
	private Integer stockid;
	
	@Column(name = "TIMESTAMP", nullable = false)
	private Date timestamp;
	
	public NewsDtoKey(){
		
	}

	public Integer getStockid() {
		return stockid;
	}

	public void setStockid(Integer stockid) {
		this.stockid = stockid;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
