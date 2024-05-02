package com.orkhanmamedov.expressbank.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orkhanmamedov.expressbank.MockData;
import com.orkhanmamedov.expressbank.client.StockClient;
import com.orkhanmamedov.expressbank.dto.stock.response.StockResponseDto;
import com.orkhanmamedov.expressbank.parser.impl.StocksParserImpl;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

@ExtendWith(MockitoExtension.class)
public class StockParserTest {

  @Mock private StockClient stockClient;
  @Spy private ObjectMapper objectMapper;
  @InjectMocks private StocksParserImpl stocksParser;

  @Test
  void whenRequestGetStocks_thenReturnsListOfStocks() throws Exception {
    // given
    String json =
        new String(
            new ClassPathResource("static/sample_stocks.json").getInputStream().readAllBytes());
    List<StockResponseDto> expected = MockData.getValidListStocks();

    // when
    when(stockClient.getStocks(anyString(), anyString())).thenReturn(json);
    // then
    List<StockResponseDto> parsed =
        stocksParser.parse(stockClient.getStocks(anyString(), anyString()));

    assertEquals(expected.get(0).stockSymbol(), parsed.get(0).stockSymbol());
    assertEquals(expected.get(0).price(), parsed.get(0).price());
    assertEquals(expected.get(0).lastUpdateTime(), parsed.get(0).lastUpdateTime());
    assertEquals(expected.get(1).stockSymbol(), parsed.get(1).stockSymbol());
    assertEquals(expected.get(1).price(), parsed.get(1).price());
    assertEquals(expected.get(1).lastUpdateTime(), parsed.get(1).lastUpdateTime());
    assertEquals(expected.get(2).stockSymbol(), parsed.get(2).stockSymbol());
    assertEquals(expected.get(2).price(), parsed.get(2).price());
    assertEquals(expected.get(2).lastUpdateTime(), parsed.get(2).lastUpdateTime());
  }
}
