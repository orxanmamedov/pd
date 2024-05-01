package com.orkhanmamedov.expressbank.parser;

import com.orkhanmamedov.expressbank.dto.stock.response.StockResponseDto;
import java.util.List;

public interface Parser {
  List<StockResponseDto> parse(String json);
}
