package com.bamdoliro.gati.domain.user.facade;

import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.domain.repository.UserRepository;
import com.bamdoliro.gati.domain.user.domain.type.Authority;
import com.bamdoliro.gati.domain.user.domain.type.Gender;
import com.bamdoliro.gati.domain.user.domain.type.Status;
import com.bamdoliro.gati.domain.user.exception.PasswordMismatchException;
import com.bamdoliro.gati.domain.user.exception.UserAlreadyExistsException;
import com.bamdoliro.gati.domain.user.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Facade] User")
class UserFacadeTest {

    @InjectMocks
    private UserFacade userFacade;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private final User defaultUser = User.builder()
            .email("gati@bamdoliro.com")
            .name("김가티")
            .password("12345678910")
            .authority(Authority.ROLE_USER)
            .gender(Gender.FEMALE)
            .birth(LocalDate.of(2022,2,2))
            .status(Status.NOT_VERIFIED)
            .build();

    @DisplayName("[Facade] findUserByEmail")
    @Test
    void givenEmail_whenFindUser_thenReturnsUser() {
        // given
        given(userRepository.findByEmail(anyString())).willReturn(Optional.of(defaultUser));

        // when
        User foundUser = userFacade.findUserByEmail("gati@bamdoliro.com");

        // then
        verify(userRepository, times(1)).findByEmail(anyString());
        assertEquals("gati@bamdoliro.com", foundUser.getEmail());
        assertEquals("12345678910", foundUser.getPassword());
        assertEquals(Status.NOT_VERIFIED, foundUser.getStatus());
    }

    @DisplayName("[Facade] findUserByEmail - 유저가 존재하지 않는 경우")
    @Test
    void givenInvalidEmail_whenFindUser_thenThrowsUserNotFoundException() {
        // given
        given(userRepository.findByEmail(anyString())).willThrow(UserNotFoundException.class);

        // when and then
        assertThrows(UserNotFoundException.class, () -> userFacade.findUserByEmail("gati@bamdoliro.com"));
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @DisplayName("[Facade] checkUserByEmail")
    @Test
    void givenEmail_whenCheckUser_thenChecksSuccessfully() {
        // given
        given(userRepository.findByEmail(anyString())).willReturn(Optional.empty());

        // when
        userFacade.checkUserByEmail("gati@bamdoliro.com");

        // then
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @DisplayName("[Facade] checkUserByEmail - 유저가 이미 존재하는 경우")
    @Test
    void givenInvalidEmail_whenCheckUser_thenThrowsUserAlreadyExistException() {
        // given
        given(userRepository.findByEmail(anyString())).willReturn(Optional.of(defaultUser));

        // when and then
        assertThrows(UserAlreadyExistsException.class,
                () -> userFacade.checkUserByEmail("gati@bamdoliro.com"));
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @DisplayName("[Facade] checkUserPassword")
    @Test
    void givenUserAndEnteredPassword_whenCheckPassword_thenChecksSuccessfully() {
        // given
        given(passwordEncoder.matches(anyString(), anyString())).willReturn(true);

        // when
        userFacade.checkUserPassword(defaultUser, "12345678910");

        // then
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
    }

    @DisplayName("[Facade] checkUserPassword - 비밀번호가 틀린 경우")
    @Test
    void givenUserAndWrongEnteredPassword_whenCheckPassword_thenThrowsPasswordMismatchException() {
        // given
        given(passwordEncoder.matches(anyString(), anyString())).willReturn(false);

        // when and then
        assertThrows(PasswordMismatchException.class,
                () -> userFacade.checkUserPassword(defaultUser, "12345678910"));
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
    }
}