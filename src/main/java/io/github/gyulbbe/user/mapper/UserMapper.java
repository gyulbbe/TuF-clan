package io.github.gyulbbe.user.mapper;

import io.github.gyulbbe.user.dto.RegisterUserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insertUser(RegisterUserDto registerUserDto);
}
