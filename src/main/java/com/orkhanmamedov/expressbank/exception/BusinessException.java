package com.orkhanmamedov.expressbank.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

  public static final String EMAIL_IS_NOT_AVAILABLE = "Email is not available to register";
  public static final String PESEL_IS_NOT_AVAILABLE = "Pesel is not available to register";
  public static final String PHONE_NUMBER_INVALID = "Phone number is invalid";
  public static final String USER_ALREADY_EXISTS = "User already exists";

  private final HttpStatus httpStatus;

  public BusinessException(final HttpStatus httpStatus, final String message) {
    super(message);
    this.httpStatus = httpStatus;
  }
}
