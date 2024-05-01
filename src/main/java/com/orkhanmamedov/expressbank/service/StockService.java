package com.orkhanmamedov.expressbank.service;

import com.orkhanmamedov.expressbank.dto.stock.response.StockResponseDto;
import java.util.List;

public interface StockService {
  List<StockResponseDto> getStocks();
}
