package com.bamdoliro.gati.domain.user.service;

import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.domain.repository.UserRepository;
import com.bamdoliro.gati.domain.user.domain.type.Authority;
import com.bamdoliro.gati.domain.user.domain.type.Gender;
import com.bamdoliro.gati.domain.user.domain.type.UserStatus;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import com.bamdoliro.gati.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.bamdoliro.gati.domain.user.presentation.dto.request.UpdateUserNameRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] User")
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserFacade userFacade;
    @Mock
    private PasswordEncoder passwordEncoder;

    private final User defaultUser = User.builder()
            .email("gati@bamdoliro.com")
            .name("김가티")
            .password("12345678910")
            .authority(Authority.ROLE_USER)
            .gender(Gender.FEMALE)
            .birth(LocalDate.of(2022,2,2))
            .status(UserStatus.NOT_VERIFIED)
            .build();

    @DisplayName("[Service] User 생성")
    @Test
    void givenCreateUserDto_whenCreatingUser_thenCreatesUser() {
        // given
        given(userRepository.save(any())).willReturn(defaultUser);
        given(passwordEncoder.encode(any())).willReturn("12345678910");
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        // when
        userService.createUser(
                new CreateUserRequestDto(
                        "gati@bamdoliro.com",
                        "12234910",
                        "김가티",
                        2022, 2, 2,
                        Gender.FEMALE
                )
        );

        // then
        verify(userRepository, times(1)).save(captor.capture());
        User savedUser = captor.getValue();
        assertEquals("gati@bamdoliro.com", savedUser.getEmail());
        assertEquals("12345678910", savedUser.getPassword());
        assertEquals("김가티", savedUser.getName());
        assertEquals(LocalDate.of(2022, 2, 2), savedUser.getBirth());
        assertEquals(Gender.FEMALE, savedUser.getGender());
        assertEquals(Authority.ROLE_USER, savedUser.getAuthority());
    }

    @DisplayName("[Service] user name 변경")
    @Test
    void givenName_whenUpdatingName_thenUpdatesUserName() {
        // given
        given(userFacade.getCurrentUser()).willReturn(defaultUser);

        // when
        userService.updateUserName(new UpdateUserNameRequestDto("킴가틴데염"));

        // then
        assertEquals("킴가틴데염", defaultUser.getName());

    }

}