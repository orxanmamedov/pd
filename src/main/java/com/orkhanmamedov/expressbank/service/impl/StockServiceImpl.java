package com.orkhanmamedov.expressbank.service.impl;

import com.orkhanmamedov.expressbank.client.StockClient;
import com.orkhanmamedov.expressbank.dto.stock.response.StockResponseDto;
import com.orkhanmamedov.expressbank.parser.Parser;
import com.orkhanmamedov.expressbank.service.StockService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
  @Value("${feign.stock.api-key}")
  String apiKey;

  @Value("${feign.stock.api-host}")
  String apiHost;

  private final StockClient stockClient;
  private final Parser parser;

  @Override
  public List<StockResponseDto> getStocks() {
    log.info("{StockService -> getStocks} -> Request to get stocks");

    return parser.parse(stockClient.getStocks(apiKey, apiHost));
  }
}
