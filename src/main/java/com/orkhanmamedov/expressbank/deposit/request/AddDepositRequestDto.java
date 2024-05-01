package com.orkhanmamedov.expressbank.deposit.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Builder;

@Builder(toBuilder = true)
public record AddDepositRequestDto(@Schema(example = "1000.25") BigDecimal depositAmount) {}
