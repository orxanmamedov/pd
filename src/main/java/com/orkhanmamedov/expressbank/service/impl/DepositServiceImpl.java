package com.orkhanmamedov.expressbank.service.impl;

import com.orkhanmamedov.expressbank.dto.deposit.request.AddDepositRequestDto;
import com.orkhanmamedov.expressbank.entity.UserEntity;
import com.orkhanmamedov.expressbank.exception.BusinessException;
import com.orkhanmamedov.expressbank.repository.UserRepository;
import com.orkhanmamedov.expressbank.service.DepositService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {
  private final UserRepository userRepository;

  @Override
  public void addDeposit(String userId, AddDepositRequestDto dto) {

    UserEntity userEntity =
        userRepository
            .findById(userId)
            .orElseThrow(
                () ->
                    new BusinessException(
                        HttpStatus.BAD_REQUEST, BusinessException.USER_NOT_FOUND));
    userEntity.setDepositBalance(dto.depositAmount());
    userRepository.save(userEntity);

    log.info("Deposit has been added to user {}", userId);
  }
}
