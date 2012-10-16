package com.ubs.gtp.data.domain.client;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ADVISOR")
public class AdvisorDto implements Serializable {

    private static final long serialVersionUID = 1355933269423003948L;

    @Id
    @Column(name = "ADVISOR_ID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int advisorId;
    
    @Column(name = "SAMACCOUNTNAME")
    private String samAccountName;

    /**
     * Empty constructor.
     */
    public AdvisorDto() {
    }

    /**
     * Gets the advisorId.
     *
     * @return the advisorId.
     */
    public int getAdvisorId() {
        return advisorId;
    }

    /**
     * Sets the advisorId.
     *
     * @param advisorId new advisorId.
     */
    public void setAdvisorId(final int advisorId) {
        this.advisorId = advisorId;
    }
    
    /**
     * Gets the samAccountName.
     *
     * @return the samAccountName.
     */
    public String getSamAccountName() {
    	return samAccountName;
    }
    
    /**
     * Sets the samAccountName.
     *
     * @param samAccountName new samAccountName.
     */
    public void setSamAccountName(String samAccountName) {
    	this.samAccountName = samAccountName;
    }
}
