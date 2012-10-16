package com.ubs.gtp.data.source;

import com.google.common.base.Objects;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.math.BigDecimal;

import static com.google.common.base.Objects.ToStringHelper;

/**
 * Maps to a Yahoo quote.
 *
 * @author Jakub D Kozlowski
 * @since 0.4
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class YahooQuote {

    @JsonProperty("symbol")
    private String ric;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Ask")
    private BigDecimal ask;

    @JsonProperty("Bid")
    private BigDecimal bid;

    @JsonProperty("Open")
    private BigDecimal openingPrice;

    @JsonProperty("EarningsShare")
    private BigDecimal eps;

    @JsonProperty("YearLow")
    private BigDecimal yearLow;

    @JsonProperty("YearHigh")
    private BigDecimal yearHigh;

    public String getRic() {
        return ric;
    }

    public void setRic(final String ric) {
        this.ric = ric;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(BigDecimal openingPrice) {
        this.openingPrice = openingPrice;
    }

    public BigDecimal getEps() {
        return eps;
    }

    public void setEps(BigDecimal eps) {
        this.eps = eps;
    }

    public BigDecimal getYearLow() {
        return yearLow;
    }

    public void setYearLow(BigDecimal yearLow) {
        this.yearLow = yearLow;
    }

    public BigDecimal getYearHigh() {
        return yearHigh;
    }

    public void setYearHigh(BigDecimal yearHigh) {
        this.yearHigh = yearHigh;
    }

    @Override
    public String toString() {
        final ToStringHelper toString = Objects.toStringHelper(this.getClass());
        toString.add("ric", ric);
        toString.add("name", name);
        toString.add("ask", ask);
        toString.add("bid", bid);
        toString.add("openingPrice", openingPrice);
        toString.add("eps", eps);
        toString.add("yearLow", yearLow);
        toString.add("yearHigh", yearHigh);
        return toString.toString();
    }
}
