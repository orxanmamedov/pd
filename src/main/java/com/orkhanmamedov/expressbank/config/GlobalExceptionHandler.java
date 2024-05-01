package com.orkhanmamedov.expressbank.config;

import com.orkhanmamedov.expressbank.dto.common.MessageResponseDto;
import com.orkhanmamedov.expressbank.dto.common.ValidationExceptionResponseDto;
import com.orkhanmamedov.expressbank.exception.BusinessException;
import com.orkhanmamedov.expressbank.exception.SecurityException;
import com.orkhanmamedov.expressbank.exception.TechnicalException;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<MessageResponseDto> handleBusinessException(final BusinessException ex) {
    log.warn("{handleBusinessException} -> {}", ex.getMessage());

    return ResponseEntity.status(ex.getHttpStatus()).body(new MessageResponseDto(ex.getMessage()));
  }

  @ExceptionHandler(SecurityException.class)
  public ResponseEntity<MessageResponseDto> handleSecurityException(final SecurityException ex) {
    log.warn("{handleSecurityException} -> {}", ex.getMessage());

    return ResponseEntity.status(ex.getHttpStatus()).body(new MessageResponseDto(ex.getMessage()));
  }

  @ExceptionHandler(TechnicalException.class)
  public ResponseEntity<MessageResponseDto> handleTechnicalException(final TechnicalException ex) {
    log.warn("{handleTechnicalException} -> {}", ex.getMessage());

    return ResponseEntity.status(ex.getHttpStatus()).body(new MessageResponseDto(ex.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationExceptionResponseDto> handleMethodArgumentNotValidException(
          final MethodArgumentNotValidException ex) {

    Optional<FieldError> fieldError =
            Optional.ofNullable(ex)
                    .map(MethodArgumentNotValidException::getBindingResult)
                    .map(Errors::getFieldError);

    String message = fieldError.map(FieldError::getDefaultMessage).orElse(StringUtils.EMPTY);

    String field = fieldError.map(FieldError::getField).orElse(StringUtils.EMPTY);

    log.warn("{handleMethodArgumentNotValidException} -> message = {}, field = {}", message, field);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ValidationExceptionResponseDto(message, field));
  }
}
