package com.orkhanmamedov.expressbank.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException {
  public static final String USER_NOT_FOUND = "User with such id (%s) is not found";
  private final HttpStatus httpStatus;

  public NotFoundException(final String message) {
    super(message);
    this.httpStatus = HttpStatus.NOT_FOUND;
  }
}
