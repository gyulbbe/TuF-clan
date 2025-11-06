package io.github.gyulbbe.user.service;

import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.user.dto.RegisterUserDto;
import io.github.gyulbbe.user.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper userMapper;

    public ResponseDto<Void> insertUser(RegisterUserDto registerUserDto) {
        int result = userMapper.insertUser(registerUserDto);
        if (result > 0) {
            return ResponseDto.success(null);
        } else {
            return ResponseDto.fail("회원가입에 실패했습니다.");
        }
    }
}
