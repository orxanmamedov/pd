package com.orkhanmamedov.expressbank.unit;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.orkhanmamedov.expressbank.MockData;
import com.orkhanmamedov.expressbank.dto.deposit.request.AddDepositRequestDto;
import com.orkhanmamedov.expressbank.entity.UserEntity;
import com.orkhanmamedov.expressbank.exception.BusinessException;
import com.orkhanmamedov.expressbank.repository.UserRepository;
import com.orkhanmamedov.expressbank.service.impl.DepositServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class DepositServiceTest {

  private AddDepositRequestDto addDepositRequest;
  private UserEntity userEntity;
  @Mock private UserRepository userRepository;

  @InjectMocks private DepositServiceImpl depositService;

  @BeforeEach
  public void setup() {
    addDepositRequest = MockData.getValidAddDepositRequestDto();
    userEntity = MockData.getValidUserEntity();
  }

  @Test
  void whenAddDeposit_thenSuccess() {
    // given
    String userId = "user123";

    when(userRepository.findById(anyString())).thenReturn(Optional.of(userEntity));
    when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

    // when
    depositService.addDeposit(userId, addDepositRequest);

    // then
    verify(userRepository, times(1)).findById(userId);
    verify(userRepository, times(1)).save(userEntity);
  }

  @Test
  void whenAddDepositUserNotExist_thenUserNotFound() {
    // given
    String userId = "nonExistentUser";

    when(userRepository.findById(anyString())).thenReturn(Optional.empty());

    // when, then
    BusinessException exception =
        org.junit.jupiter.api.Assertions.assertThrows(
            BusinessException.class, () -> depositService.addDeposit(userId, addDepositRequest));
    org.junit.jupiter.api.Assertions.assertEquals(
        HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    org.junit.jupiter.api.Assertions.assertEquals(
        BusinessException.USER_NOT_FOUND, exception.getMessage());
  }
}
