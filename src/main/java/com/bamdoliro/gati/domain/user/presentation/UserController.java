package com.bamdoliro.gati.domain.user.presentation;

import com.bamdoliro.gati.domain.user.presentation.dto.request.*;
import com.bamdoliro.gati.domain.user.presentation.dto.response.TokenResponseDto;
import com.bamdoliro.gati.domain.user.presentation.dto.response.getUserResponseDto;
import com.bamdoliro.gati.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public void createUser(@RequestBody @Valid CreateUserRequestDto dto) {
        userService.createUser(dto);
    }

    @PostMapping("/login")
    public TokenResponseDto loginUser(
            @RequestBody @Valid LoginUserRequestDto dto,
            HttpServletResponse response
    ) {
        return userService.loginUser(dto, response);
    }

    @PostMapping("/logout")
    public void logoutUser(HttpServletResponse response) {
        userService.logoutUser(response);
    }

    @GetMapping
    public getUserResponseDto getUser() {
        return userService.getUserInformation();
    }

    @PutMapping("/update/password")
    public void updateUserPassword(@RequestBody @Valid UpdateUserPasswordRequestDto dto) {
        userService.updateUserPassword(dto);
    }

    @PutMapping("/update/name")
    public void updateUserName(@RequestBody @Valid UpdateUserNameRequestDto dto) {
        userService.updateUserName(dto);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody @Valid DeleteUserRequestDto dto) {
        userService.deleteUser(dto);
    }
}
