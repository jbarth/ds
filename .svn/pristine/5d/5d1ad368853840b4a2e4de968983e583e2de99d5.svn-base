package com.ubs.gtp.data.domain.client;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

import static com.google.common.base.Objects.ToStringHelper;

/**
 * Entity for {@code STOCK_RIC} table in the database.
 *
 * @author Jakub D Kozlowski
 * @author Alexandra Ojha
 * @since 0.4
 */
@Entity
@Table(name = "STOCK_RIC")
public class StockRicDto implements Serializable {

    private static final long serialVersionUID = 1351253453242300345L;

    @Id
    @Column(name = "RIC", nullable = false)
    private String ric;

    /**
     * Empty constructor.
     */
    public StockRicDto() {
    }

    /**
     * Default constructor.
     *
     * @param ric the ric.
     */
    public StockRicDto(final String ric) {
        this.ric = ric;
    }

    /**
     * Gets the ric.
     *
     * @return the ric.
     */
    public String getRic() {
        return ric;
    }

    /**
     * Sets the ric.
     *
     * @param ric new ric.
     */
    public void setRic(String ric) {
        this.ric = ric;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final ToStringHelper toString = Objects.toStringHelper(this.getClass());
        toString.add("ric", ric);
        return toString.toString();
    }
}
