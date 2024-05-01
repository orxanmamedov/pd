package com.orkhanmamedov.expressbank.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TechnicalException extends RuntimeException {

  public static final String NO_CONFIRMATION_STRATEGY_OPERATION =
      "No confirmation strategy found for this operation: %s";
  private final HttpStatus httpStatus;

  public TechnicalException(final HttpStatus httpStatus, final String message) {
    super(message);
    this.httpStatus = httpStatus;
  }
}
