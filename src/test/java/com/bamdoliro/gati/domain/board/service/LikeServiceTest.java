package com.bamdoliro.gati.domain.board.service;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.BoardLike;
import com.bamdoliro.gati.domain.board.domain.repository.LikeRepository;
import com.bamdoliro.gati.domain.board.facade.BoardFacade;
import com.bamdoliro.gati.domain.board.facade.LikeFacade;
import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.domain.type.Authority;
import com.bamdoliro.gati.domain.user.domain.type.Gender;
import com.bamdoliro.gati.domain.user.domain.type.UserStatus;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

    @Mock private BoardFacade boardFacade;
    @Mock private UserFacade userFacade;
    @Mock private LikeFacade likeFacade;
    @Mock private LikeRepository likeRepository;

    @InjectMocks private LikeService likeService;

    Community community = Community.builder()
            .name("우리집")
            .introduction("킄")
            .numberOfPeople(100)
            .isPublic(true)
            .build();

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
            .community(community)
            .build();

    @DisplayName("[Service] 게시물 좋아요")
    @Test
    void likeTest() {
        // Given
        final int beforeNumberOfLike = 0;

        given(boardFacade.findBoardById(anyLong())).willReturn(board);
        given(userFacade.getCurrentUser()).willReturn(user);

        ArgumentCaptor<BoardLike> captor = ArgumentCaptor.forClass(BoardLike.class);

        // When
        likeService.like(anyLong());

        // Then
        verify(likeRepository, times(1))
                .save(captor.capture());

        BoardLike savedBoardLike = captor.getValue();

        assertEquals(beforeNumberOfLike + 1, board.getLikes().size());
        assertEquals(board, savedBoardLike.getBoard());
        assertEquals(user, savedBoardLike.getLiker());
    }
}