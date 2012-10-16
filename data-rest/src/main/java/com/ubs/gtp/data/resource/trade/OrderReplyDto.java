package com.ubs.gtp.data.resource.trade;

import java.math.BigDecimal;


/**
 * Dto for sending order confirmations for both buy and sell orders
 *
 * @author Mihkel Aamer
 * @since 0.1
 */
public final class OrderReplyDto {

    private boolean success;
    private BigDecimal executionprice;

    public OrderReplyDto(boolean success, BigDecimal executionprice) {
        this.success = success;
        this.executionprice = executionprice;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public BigDecimal getExecutionprice() {
        return executionprice;
    }

    public void setExecutionprice(BigDecimal buyingprice) {
        this.executionprice = buyingprice;
    }
}
