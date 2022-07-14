package com.idfinance.task.service.mapper;

import com.idfinance.task.dto.UserDto;
import com.idfinance.task.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User entity);
}
