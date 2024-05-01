package com.orkhanmamedov.expressbank.service.impl;

import com.orkhanmamedov.expressbank.dto.user.request.UserRegistrationRequestDto;
import com.orkhanmamedov.expressbank.dto.user.response.UserDataResponseDto;
import com.orkhanmamedov.expressbank.entity.RoleEntity;
import com.orkhanmamedov.expressbank.entity.UserEntity;
import com.orkhanmamedov.expressbank.exception.SecurityException;
import com.orkhanmamedov.expressbank.mapper.UserMapper;
import com.orkhanmamedov.expressbank.repository.UserRepository;
import com.orkhanmamedov.expressbank.service.UserService;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  @Transactional
  public void saveUser(UserRegistrationRequestDto userDto) {
    log.info("{saveUser} -> Save user");

    final UserEntity user = userMapper.toEntity(userDto);
    final RoleEntity role = new RoleEntity();
    role.setId(1L);
    user.setRoles(Set.of(role));
    userRepository.save(user);
  }

  @Override
  public UserDataResponseDto getUserDataById(final String userId) {
    log.info("{getUserDataById} -> Receiving user data");

    return userMapper.toDto(
        userRepository
            .findById(userId)
            .orElseThrow(
                () ->
                    new SecurityException(
                        HttpStatus.BAD_REQUEST,
                        String.format(SecurityException.USER_DOESNT_EXIST, userId))));
  }
}
