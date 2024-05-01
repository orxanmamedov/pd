package com.orkhanmamedov.expressbank.client;

import com.orkhanmamedov.expressbank.config.WebMvcConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
    url = "${feign.stock.url}",
    path = "${feign.stock.path}",
    name = "stock-api",
    configuration = WebMvcConfig.class)
public interface StockClient {
  @GetMapping(
      headers = "Accept: " + MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  String getStocks(
      @RequestHeader("X-RapidAPI-Key") String apiKey,
      @RequestHeader("X-RapidAPI-Host") String apiHost);
}
