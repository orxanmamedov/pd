package com.orkhanmamedov.expressbank.interceptor;

import java.security.Principal;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

  private final AuthenticationHolder authenticationHolder;

  public static class Context {
    public static SecurityContext getContext() {
      return SecurityContextHolder.getContext();
    }
  }

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    Optional.ofNullable(Context.getContext().getAuthentication())
        .map(Principal::getName)
        .ifPresent(
            userId -> {
              response.setHeader("authenticated-user-id", userId);
              authenticationHolder.setUserId(userId);
              MDC.put("user_id", userId);
            });
    return true;
  }
}
