package com.ubs.gtp.data.domain.instruments;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import static com.google.common.base.Objects.ToStringHelper;

/**
 * Entity for {@code STOCK_INFO} table in the database.
 *
 * @author Jakub D Kozlowski
 * @author Alexanda P Ojha
 * @since 0.3
 */
@Entity
@Table(name = "STOCK_INFO")
public class StockDto implements Serializable {

    private static final long serialVersionUID = 1355933269423003948L;

    @Id
    @Column(name = "STOCK_ID", nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;

    @Column(name = "RIC", nullable = false)
    private String ric;

    @Column(name = "TIMESTAMP", nullable = false)
    private Calendar timestamp;

    @Column(name = "COMPANY_NAME", nullable = false)
    private String name;

    @Column(name = "CURRENT_PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "OPENING_PRICE")
    private BigDecimal openingprice;

    @Column(name = "INDUSTRY")
    private String industry;

    @Column(name = "EPS")
    private BigDecimal eps;

    @Column(name = "YEAR_LOW")
    private BigDecimal yearlow;

    @Column(name = "YEAR_HIGH")
    private BigDecimal yearhigh;

    /**
     * Empty constructor.
     */
    public StockDto() {
    }

    /**
     * Default contsructor.
     *
     * @param ric       ric of the stock.
     * @param timestamp timestamp of last price update.
     * @param name      name of the company.
     * @param price     current price.
     */
    public StockDto(final String ric,
                    final BigDecimal price,
                    final String name,
                    final Calendar timestamp) {
        this.ric = ric;
        this.timestamp = timestamp;
        this.name = name;
        this.price = price;
    }

    /**
     * Default contsructor.
     *
     * @param ric       ric of the stock.
     * @param timestamp timestamp of last price update.
     * @param name      name of the company.
     * @param price     current price.
     * @param industry  industry of the company.
     * @param eps       earnings per share.
     * @param yearlow   lowest price in the past year.
     * @param yearhigh  highest price in the past year.
     */
    public StockDto(final String ric,
                    final Calendar timestamp,
                    final String name,
                    final BigDecimal price,
                    final String industry,
                    final BigDecimal eps,
                    final BigDecimal yearlow,
                    final BigDecimal yearhigh) {
        this.ric = ric;
        this.timestamp = timestamp;
        this.name = name;
        this.price = price;
        this.industry = industry;
        this.eps = eps;
        this.yearlow = yearlow;
        this.yearhigh = yearhigh;
    }

    /**
     * Gets the id.
     *
     * @return the id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id new id.
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Sets the ric.
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
    public void setRic(final String ric) {
        this.ric = ric;
    }

    /**
     * Gets the timestamp.
     *
     * @return the timestamp.
     */
    public Calendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp.
     *
     * @param timestamp new timestamp.
     */
    public void setTimestamp(final Calendar timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name new name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the price.
     *
     * @return the price.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price.
     *
     * @param price new price.
     */
    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOpeningprice() {
        return openingprice;
    }

    public void setOpeningprice(BigDecimal openingprice) {
        this.openingprice = openingprice;
    }

    /**
     * Gets the industry.
     *
     * @return the industry.
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * Sets the industry.
     *
     * @param industry new industry.
     */
    public void setIndustry(final String industry) {
        this.industry = industry;
    }

    /**
     * Gets the eps.
     *
     * @return the eps.
     */
    public BigDecimal getEps() {
        return eps;
    }

    /**
     * Sets the eps.
     *
     * @param eps new eps.
     */
    public void setEps(final BigDecimal eps) {
        this.eps = eps;
    }

    /**
     * Gets the yearlow.
     *
     * @return the yearlow.
     */
    public BigDecimal getYearlow() {
        return yearlow;
    }

    /**
     * Sets the yearlow.
     *
     * @param yearlow new yearlow.
     */
    public void setYearlow(final BigDecimal yearlow) {
        this.yearlow = yearlow;
    }

    /**
     * Gets the yearhigh.
     *
     * @return the yearhigh.
     */
    public BigDecimal getYearhigh() {
        return yearhigh;
    }

    /**
     * Sets the yearhigh.
     *
     * @param yearhigh new yearhigh.
     */
    public void setYearhigh(final BigDecimal yearhigh) {
        this.yearhigh = yearhigh;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final ToStringHelper toString = Objects.toStringHelper(this.getClass());
        toString.add("id", id);
        toString.add("ric", ric);
        toString.add("timestamp", timestamp.getTime().getTime());
        toString.add("name", name);
        toString.add("price", price);
        toString.add("industry", industry);
        toString.add("eps", eps);
        toString.add("yearlow", yearlow);
        toString.add("yearhigh", yearhigh);
        return toString.toString();
    }
}
