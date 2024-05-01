package com.orkhanmamedov.expressbank;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ExpressBank PayDay API", version = "1.0.0"))
@EnableFeignClients
public class ExpressBankApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExpressBankApplication.class, args);
  }
}
