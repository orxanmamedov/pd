package com.orkhanmamedov.expressbank.dto.stock.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
public record StockResponseDto(
    @Schema(example = "NIFTY 50", description = "The symbol of the stock") String stockSymbol,
    @Schema(example = "22416.5", description = "The price of the stock") Double price,
    @Schema(
            example = "03-May-2024 13:26:44",
            description = "The last update time of the stock price")
        String lastUpdateTime) {}
