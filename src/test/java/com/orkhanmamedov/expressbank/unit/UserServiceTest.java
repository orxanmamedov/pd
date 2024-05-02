package com.orkhanmamedov.expressbank.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.orkhanmamedov.expressbank.MockData;
import com.orkhanmamedov.expressbank.dto.user.request.UserRequestDto;
import com.orkhanmamedov.expressbank.entity.UserEntity;
import com.orkhanmamedov.expressbank.mapper.UserMapper;
import com.orkhanmamedov.expressbank.repository.UserRepository;
import com.orkhanmamedov.expressbank.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  private UserRequestDto request;
  private UserEntity userEntity;
  @Mock private UserRepository userRepository;
  @Mock private UserMapper userMapper;
  @InjectMocks private UserServiceImpl userService;

  @BeforeEach
  public void setup() {
    request = MockData.getValidUserRequestDto();
    userEntity = MockData.getValidUserEntity();
  }

  @Test
  void saveUserTest() {
    // When
    when(userMapper.toEntity(request)).thenReturn(userEntity);
    when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
    userService.saveUser(request);

    // Then
    verify(userMapper, times(1)).toEntity(request);
    verify(userRepository, times(1)).save(any(UserEntity.class));
  }
}
