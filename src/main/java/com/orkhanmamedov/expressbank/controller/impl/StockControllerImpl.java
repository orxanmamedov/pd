package com.orkhanmamedov.expressbank.controller.impl;

import com.orkhanmamedov.expressbank.controller.StockController;
import com.orkhanmamedov.expressbank.dto.stock.response.StockResponseDto;
import com.orkhanmamedov.expressbank.service.StockService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(StockController.STOCKS_URL)
public class StockControllerImpl implements StockController {
  private final StockService stockService;

  @Override
  @GetMapping
  public List<StockResponseDto> getStocks() {
    log.info("{StockController -> getStocks} Request for getting stocks");

    return stockService.getStocks();
  }
}
