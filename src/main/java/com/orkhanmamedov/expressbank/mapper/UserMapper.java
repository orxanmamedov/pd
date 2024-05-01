package com.orkhanmamedov.expressbank.mapper;

import com.orkhanmamedov.expressbank.dto.user.request.UserRegistrationRequestDto;
import com.orkhanmamedov.expressbank.dto.user.response.UserDataResponseDto;
import com.orkhanmamedov.expressbank.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserEntity toEntity(final UserRegistrationRequestDto dto);

  UserDataResponseDto toDto(final UserEntity entity);
}
