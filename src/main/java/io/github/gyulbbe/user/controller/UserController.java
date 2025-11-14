package io.github.gyulbbe.user.controller;

import io.github.gyulbbe.common.dto.ResponseDto;
import io.github.gyulbbe.user.dto.CustomUserDetails;
import io.github.gyulbbe.user.dto.RegisterUserDto;
import io.github.gyulbbe.user.dto.UserInsertDto;
import io.github.gyulbbe.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RequestMapping("/user")
@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/test")
    public String test() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        log.info("Username: " + username);
        log.info("Authorities size: " + authorities.size());

        if (authorities.isEmpty()) {
            return username + " (no authorities)";
        }

        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        log.info("Role: " + role);

        return username + " " + role;
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseDto<Void>> insertUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        return ResponseEntity.ok(userService.insertUser(registerUserDto));
    }

    @PostMapping("/insert-list")
    public ResponseEntity<ResponseDto<Void>> insertUserList(@Valid @RequestBody List<RegisterUserDto> userList) {
        return ResponseEntity.ok(userService.insertUserList(userList));
    }
}
