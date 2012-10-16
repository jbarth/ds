package com.ubs.gtp.data.domain.client;

import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity for {@code LOGIN} table in the database.
 *
 * @author Jakub D Kozlowski
 * @since 0.3
 */
@Entity
@Table(name = "CLIENT_LOGIN")
public class LoginDto implements Serializable {

    private static final long serialVersionUID = 1351733269423003948L;

    @JsonBackReference
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @Id
    @JoinColumn(name = "CLIENT_ID", nullable = false, insertable = false, updatable = false)
    private ClientDto client;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordhash;

    @Column(name = "PIN_HASH", nullable = false)
    private String pinhash;

    @Column(name = "FAILED_ATTEMPTS", nullable = false)
    private Integer failedattempts;

    /**
     * Empty constructor.
     */
    public LoginDto() {
    }

    /**
     * Gets the client.
     *
     * @return the client.
     */
    public ClientDto getClient() {
        return client;
    }

    /**
     * Sets the client.
     *
     * @param client new client.
     */
    public void setClient(final ClientDto client) {
        this.client = client;
    }

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
    public String getPasswordhash() {
        return passwordhash;
    }

    /**
     * Sets the passwordhash.
     *
     * @param passwordhash new passwordhash.
     */
    public void setPasswordhash(final String passwordhash) {
        this.passwordhash = passwordhash;
    }

    /**
     * Gets the pinhash.
     *
     * @return the pinhash.
     */
    public String getPinhash() {
        return pinhash;
    }

    /**
     * Sets the pinhash.
     *
     * @param pinhash new pinhash.
     */
    public void setPinhash(final String pinhash) {
        this.pinhash = pinhash;
    }

    /**
     * Gets failedattempts.
     *
     * @return the failedattempts.
     */
    public Integer getFailedattempts() {
        return failedattempts;
    }

    /**
     * Sets the failedattempts.
     *
     * @param failedattempts the failedattempts.
     */
    public void setFailedattempts(final Integer failedattempts) {
        this.failedattempts = failedattempts;
    }
}
