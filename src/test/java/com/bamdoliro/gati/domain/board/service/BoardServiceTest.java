package com.bamdoliro.gati.domain.board.service;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.repository.BoardRepository;
import com.bamdoliro.gati.domain.board.facade.BoardFacade;
import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateBoardRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardDetailDto;
import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.repository.CommunityRepository;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.community.facade.MemberFacade;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.domain.repository.UserRepository;
import com.bamdoliro.gati.domain.user.domain.type.Authority;
import com.bamdoliro.gati.domain.user.domain.type.Gender;
import com.bamdoliro.gati.domain.user.domain.type.Status;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Mock private BoardRepository boardRepository;
    @Mock private UserRepository userRepository;
    @Mock private CommunityRepository communityRepository;
    @Mock private BoardFacade boardFacade;

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
            .status(Status.NOT_VERIFIED)
            .build();

    Board board = Board.builder()
            .title("제목제목제목제목제목젬고젬고제목젬ㄱ조젬고")
            .content("내영내영냉용내용내용내용내용ㄴ애욘애요내요랜ㅇ래내요래ㅛ내욘료ㅐㄴ요ㅐ료냐요래")
            .writer(user)
            .community(community)
            .build();

    @Test
    public void test1() throws Exception {
        // given
        given(boardRepository.save(any())).willReturn(board);
        ArgumentCaptor<Board> captor = ArgumentCaptor.forClass(Board.class);

        // when
        userRepository.save(user);
        communityRepository.save(community);
        boardRepository.save(board);

        // then
        verify(boardRepository, times(1)).save(captor.capture());
        Board savedboard = captor.getValue();
        assertEquals(user, savedboard.getWriter());
        assertEquals(community, savedboard.getCommunity());
    }

    @DisplayName("[Service] Board 상세 조회")
    @Test
    void givenBoardId_whenGetDetailBoard_thenReturnsBoardDetailDto() {
        // given
        given(boardFacade.findBoardById(board.getId())).willReturn(board);

        // when
        BoardDetailDto result = boardService.getDetail(board.getId());

        // then
        verify(boardFacade, times(1)).findBoardById(board.getId());
        assertEquals(board.getWriter().getName(), result.getWriter());
        assertEquals(board.getTitle(), result.getTitle());
        assertEquals(board.getContent(), result.getContent());
    }


    
}