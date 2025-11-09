package io.github.gyulbbe.user.mapper;

import io.github.gyulbbe.user.dto.RegisterUserDto;
import io.github.gyulbbe.user.dto.UserInsertDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insertUser(RegisterUserDto registerUserDto);

    int insertUserList(List<RegisterUserDto> userList);
}
