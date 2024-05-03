package com.orkhanmamedov.expressbank.controller;

import com.orkhanmamedov.expressbank.dto.common.response.MessageResponseDto;
import com.orkhanmamedov.expressbank.dto.stock.response.StockResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.MediaType;

public interface StockController {
  String API_V1_URI = "/api/v1";
  String STOCKS_URI = "/stocks";

  String STOCKS_URL = API_V1_URI + STOCKS_URI;

  @Operation(summary = "Get list of available stock symbols")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "List of available stock symbols",
        content =
            @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = StockResponseDto.class)))),
    @ApiResponse(
        responseCode = "401",
        description = "User is not authorized",
        content =
            @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = MessageResponseDto.class)))
  })
  List<StockResponseDto> getStocks();
}
