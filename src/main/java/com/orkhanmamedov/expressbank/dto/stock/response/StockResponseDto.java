package com.orkhanmamedov.expressbank.dto.stock.response;

import lombok.Builder;

@Builder()
public record StockResponseDto(String stockSymbol, Double price, String lastUpdateTime) {}
