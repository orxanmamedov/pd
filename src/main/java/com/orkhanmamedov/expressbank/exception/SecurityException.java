package com.orkhanmamedov.expressbank.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SecurityException extends RuntimeException {

  public static final String LOGIN_ERROR = "Invalid email or password";
  public static final String EMAIL_NOT_VERIFIED_ERROR = "Email is not verified";
  private final HttpStatus httpStatus;

  public SecurityException(final HttpStatus httpStatus, final String message) {
    super(message);
    this.httpStatus = httpStatus;
  }
}
