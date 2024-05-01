package com.orkhanmamedov.expressbank.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SecurityException extends RuntimeException {

  public static final String USER_DOESNT_EXIST = "User with such credentials (%s) does not exist";
  public static final String LOGIN_ERROR = "Invalid email or password";
  private final HttpStatus httpStatus;

  public SecurityException(final HttpStatus httpStatus, final String message) {
    super(message);
    this.httpStatus = httpStatus;
  }
}
