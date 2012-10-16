package com.ubs.gtp.data.domain.client;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

import com.ubs.gtp.data.domain.security.UsernamePasswordHashLogin;

/**
 * Entity for {@code LOGIN} table in the database.
 *
 * @author Jakub D Kozlowski
 * @author Willy Lai
 * @author Felix Pflanzl
 * 
 * @since 0.3
 */
@Entity
@Table(name = "CLIENT_LOGIN")
public class ClientLoginDto implements Serializable, UsernamePasswordHashLogin {

    private static final long serialVersionUID = 1351733269423003948L;

    //Did not work...
//    @OneToOne(optional = false, fetch = FetchType.EAGER)
//    @JoinColumn(name = "CLIENT_ID", nullable = false, insertable = false, updatable = false)
//    private ClientDto client;

    @Column(name = "CLIENT_ID")
    private Integer clientId;
    
    @JsonBackReference
    @Id
    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @Column(name = "PIN_HASH", nullable = false)
    private String pinHash;

    @Column(name = "FAILED_ATTEMPTS", nullable = false)
    private Integer failedAttempts;

    
	public Integer getId() {
		return clientId;
	}
	public void setId(Integer clientId) {
		this.clientId = clientId;
	}
	
    /**
     * Empty constructor.
     */
    public ClientLoginDto() {
    }

    /**
     * Gets the client.
     *
     * @return the client.
     */
//    public ClientDto getClient() {
//        return client;
//    }

    /**
     * Sets the client.
     *
     * @param client new client.
     */
//    public void setClient(final ClientDto client) {
//        this.client = client;
//    }

    /**
     * Gets the username.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username new username.
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Gets the passwordhash.
     *
     * @return the passwordhash.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets the passwordhash.
     *
     * @param passwordhash new passwordhash.
     */
    public void setPasswordHash(final String passwordhash) {
        this.passwordHash = passwordhash;
    }

    /**
     * Gets the pinhash.
     *
     * @return the pinhash.
     */
    public String getPinHash() {
        return pinHash;
    }

    /**
     * Sets the pinhash.
     *
     * @param pinhash new pinhash.
     */
    public void setPinHash(final String pinhash) {
        this.pinHash = pinhash;
    }

    /**
     * Gets failedattempts.
     *
     * @return the failedattempts.
     */
    public Integer getFailedAttempts() {
        return failedAttempts;
    }

    /**
     * Sets the failedattempts.
     *
     * @param failedattempts the failedattempts.
     */
    public void setFailedAttempts(final Integer failedattempts) {
        this.failedAttempts = failedattempts;
    }

//	public Integer getId() {
//		return client.getClientId();
//	}

	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
}
