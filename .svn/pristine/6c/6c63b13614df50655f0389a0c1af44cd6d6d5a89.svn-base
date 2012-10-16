package com.ubs.gtp.data.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Entity for {@code STOCK_NEWS} table in the database.
 *
 * @author Mihkel Aamer
 * @since 0.4
 */
@Entity
@Table(name = "STOCK_NEWS")
public class NewsDto implements Serializable {
	
    private static final long serialVersionUID = 1355933269423003948L;
    
    @JsonIgnore
    @EmbeddedId
    private NewsDtoKey id;
    
    @Lob
    @Column(name = "HEADLINE", nullable = false)
    private String headline;
    
    @Lob
    @Column(name = "CONTENTS", nullable = true)
    private String contents;
    
    @Transient
    private String ric;
    
    @Transient
    private Date timestamp;
    
    public NewsDto() {
    }
    
    
    
	public NewsDtoKey getId() {
		return id;
	}



	public void setId(NewsDtoKey id) {
		this.id = id;
	}


	public String getRic() {
		return ric;
	}

	public void setRic(String ric) {
		this.ric = ric;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
}
