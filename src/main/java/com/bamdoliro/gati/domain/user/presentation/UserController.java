package com.bamdoliro.gati.domain.user.presentation;

import com.bamdoliro.gati.domain.user.presentation.dto.request.*;
import com.bamdoliro.gati.domain.user.presentation.dto.response.GetUserResponseDto;
import com.bamdoliro.gati.domain.user.presentation.dto.response.TokenResponseDto;
import com.bamdoliro.gati.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
            @RequestBody @Valid LoginUserRequestDto dto
    ) {
        return userService.loginUser(dto);
    }

    @PostMapping("/logout")
    public void logoutUser() {
        userService.logoutUser();
    }

    @GetMapping
    public GetUserResponseDto getUser() {
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
