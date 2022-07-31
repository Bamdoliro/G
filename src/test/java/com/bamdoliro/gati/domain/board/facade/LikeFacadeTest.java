package com.bamdoliro.gati.domain.board.facade;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.BoardLike;
import com.bamdoliro.gati.domain.board.domain.repository.LikeRepository;
import com.bamdoliro.gati.domain.board.exception.LikeOverlapException;
import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.domain.type.Authority;
import com.bamdoliro.gati.domain.user.domain.type.Gender;
import com.bamdoliro.gati.domain.user.domain.type.UserStatus;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LikeFacadeTest {

    @Mock private LikeRepository likeRepository;

    @InjectMocks private LikeFacade likeFacade;

    User user = User.builder()
            .email("gati@bamdoliro.com")
            .name("김가티")
            .password("12345678910")
            .authority(Authority.ROLE_USER)
            .gender(Gender.FEMALE)
            .birth(LocalDate.of(2022,2,2))
            .status(UserStatus.NOT_VERIFIED)
            .build();

    Board board = Board.builder()
            .title("제목제목제목제목제목젬고젬고제목젬ㄱ조젬고")
            .content("내영내영냉용내용내용내용내용ㄴ애욘애요내요랜ㅇ래내요래ㅛ내욘료ㅐㄴ요ㅐ료냐요래")
            .writer(user)
            .build();

    @DisplayName("[Facade] validateLike - 게시불에 좋아요 누르기 ")
    @Test
    void givenBoardAndUser_whenValidateLike_thenValidateSuccessfully() {
        // Given
        given(likeRepository.existsByBoardAndLiker(any(), any())).willReturn(false);

        // When
        likeFacade.validateLike(any(), any());

        // Then
        verify(likeRepository, times(1))
                .existsByBoardAndLiker(any(), any());
    }

    @DisplayName("[Facade] validateLike - 같은 게시물에 이미 좋아요를 누른 경우")
    @Test
    void givenBoardAndUser_whenValidateLike_thenThrowLikeOverlapException() {
        // Given
        given(likeRepository.existsByBoardAndLiker(any(), any())).willReturn(true);

        // When & Then
        assertThrows(LikeOverlapException.class, () -> likeFacade.validateLike(board, user));

        verify(likeRepository, times(1))
                .existsByBoardAndLiker(any(), any());
    }
}