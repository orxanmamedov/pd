package com.orkhanmamedov.expressbank.dto.stock.response;

import lombok.Builder;

@Builder(toBuilder = true)
public record StockResponseDto(String stockSymbol, Double price, String lastUpdateTime) {}
