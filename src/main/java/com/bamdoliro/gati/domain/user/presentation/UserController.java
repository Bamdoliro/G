package com.bamdoliro.gati.domain.user.presentation;

import com.bamdoliro.gati.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.bamdoliro.gati.domain.user.presentation.dto.request.DeleteUserRequestDto;
import com.bamdoliro.gati.domain.user.presentation.dto.request.UpdateUserNameRequestDto;
import com.bamdoliro.gati.domain.user.presentation.dto.request.UpdateUserPasswordRequestDto;
import com.bamdoliro.gati.domain.user.presentation.dto.response.GetUserResponseDto;
import com.bamdoliro.gati.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public void createUser(@RequestBody @Valid CreateUserRequestDto dto) {
        userService.createUser(dto);
    }

    @GetMapping
    public GetUserResponseDto getUser() {
        return userService.getUserInformation();
    }

    @PutMapping("/password")
    public void updateUserPassword(@RequestBody @Valid UpdateUserPasswordRequestDto dto) {
        userService.updateUserPassword(dto);
    }

    @PutMapping("/name")
    public void updateUserName(@RequestBody @Valid UpdateUserNameRequestDto dto) {
        userService.updateUserName(dto);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody @Valid DeleteUserRequestDto dto) {
        userService.deleteUser(dto);
    }

    @PutMapping("/profile-img")
    public void updateProfileImg(@RequestParam String img) {
        userService.UpdateProfileImg(img);
    }
}
