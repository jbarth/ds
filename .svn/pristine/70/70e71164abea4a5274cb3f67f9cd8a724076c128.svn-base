package com.ubs.gtp.data.source;

import com.google.common.base.Optional;
import com.ubs.gtp.data.domain.client.StockRicDto;
import com.ubs.gtp.data.domain.instruments.StockDto;

import java.util.List;

/**
 * Provides current prices for a list of {@link StockRicDto}s.
 *
 * @author Jakub D Kozlowski
 * @since 0.4
 */
public interface PriceService {

    /**
     * Gets {@link StockDto}s for those {@code rics}s.
     *
     * @param rics rics to get.
     * @return current {@link StockDto}s or {@link Optional#absent()} if the market is closed.
     * @throws NullPointerException     if {@code rics} is null.
     * @throws IllegalArgumentException if {@code rics} is empty.
     */
    Optional<List<StockDto>> getStockDtos(final List<StockRicDto> rics);
}
