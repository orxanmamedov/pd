package com.orkhanmamedov.expressbank.service.impl;

import com.orkhanmamedov.expressbank.dto.user.request.UserRequestDto;
import com.orkhanmamedov.expressbank.entity.RoleEntity;
import com.orkhanmamedov.expressbank.entity.UserEntity;
import com.orkhanmamedov.expressbank.mapper.UserMapper;
import com.orkhanmamedov.expressbank.repository.UserRepository;
import com.orkhanmamedov.expressbank.service.UserService;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  @Transactional
  public void saveUser(UserRequestDto userDto) {
    log.info("{saveUser} -> Save user");

    final UserEntity user = userMapper.toEntity(userDto);
    final RoleEntity role = new RoleEntity();
    role.setId(1L);
    user.setRoles(Set.of(role));
    userRepository.save(user);
  }
}
