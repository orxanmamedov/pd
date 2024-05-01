package com.orkhanmamedov.expressbank.parser.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orkhanmamedov.expressbank.dto.stock.response.StockResponseDto;
import com.orkhanmamedov.expressbank.parser.Parser;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StocksParserImpl implements Parser {
  private final ObjectMapper objectMapper;

  @Override
  public List<StockResponseDto> parse(String json) {
    try {
      JsonNode rootNode = objectMapper.readTree(json);
      List<StockResponseDto> stocks = new ArrayList<>();
      rootNode.forEach(node -> stocks.add(parseStock(node)));
      return stocks;
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Failed to parse JSON: " + e.getMessage());
    }
  }

  private StockResponseDto parseStock(JsonNode stockNode) {
    return StockResponseDto.builder()
        .stockSymbol(stockNode.get("symbol").asText())
        .price(stockNode.get("lastPrice").asDouble())
        .lastUpdateTime(stockNode.get("lastUpdateTime").asText())
        .build();
  }
}
