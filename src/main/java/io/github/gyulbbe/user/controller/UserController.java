package io.github.gyulbbe.user.controller;

import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.user.dto.RegisterUserDto;
import io.github.gyulbbe.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/user")
@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/test")
    public void test() {
        System.out.println("테스트");
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseDto<Void>> insertUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        return ResponseEntity.ok(userService.insertUser(registerUserDto));
    }
}
