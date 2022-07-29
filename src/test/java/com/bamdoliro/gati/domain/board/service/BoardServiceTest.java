package com.bamdoliro.gati.domain.board.service;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.board.domain.repository.BoardRepository;
import com.bamdoliro.gati.domain.board.domain.type.board.BoardStatus;
import com.bamdoliro.gati.domain.board.facade.BoardFacade;
import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateBoardRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.request.UpdateBoardRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.BoardDetailDto;
import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.facade.CommunityFacade;
import com.bamdoliro.gati.domain.community.facade.MemberFacade;
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
import org.springframework.boot.convert.DataSizeUnit;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] Board")
class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Mock private BoardRepository boardRepository;
    @Mock private BoardFacade boardFacade;
    @Mock private UserFacade userFacade;
    @Mock private CommunityFacade communityFacade;
    @Mock private MemberFacade memberFacade;

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

    @DisplayName("[Service] 게시물 업로드")
    @Test
    public void givenBoard_whenSavePost_thenCreateBoard() throws Exception {
        // given
        given(boardRepository.save(any())).willReturn(board);
        given(userFacade.getCurrentUser()).willReturn(user);
        given(communityFacade.findCommunityById(any())).willReturn(community);
        ArgumentCaptor<Board> boardCaptor = ArgumentCaptor.forClass(Board.class);
        ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);

        // when
        CreateBoardRequestDto request = new CreateBoardRequestDto(
                1L,
                "제목제목제목제목제목젬고젬고제목젬ㄱ조젬고",
                "내영내영냉용내용내용내용내용ㄴ애욘애요내요랜ㅇ래내요래ㅛ내욘료ㅐㄴ요ㅐ료냐요래"
        );
        boardService.savePost(request);

        // then
        verify(boardRepository, times(1)).save(boardCaptor.capture());
        verify(userFacade, times(1)).getCurrentUser();
        verify(communityFacade, times(1)).findCommunityById(longCaptor.capture());

        Board savedBoard = boardCaptor.getValue();

        assertEquals(request.getTitle(), savedBoard.getTitle());
        assertEquals(request.getContent(), savedBoard.getContent());
        assertEquals(user, savedBoard.getWriter());
        assertEquals(community, savedBoard.getCommunity());
    }

    @DisplayName("[Service] 게시물 상세 조회")
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

    @DisplayName("[Service] 게시물 수정")
    @Test
    void updatePostTest() {
        // Given
        UpdateBoardRequestDto request = UpdateBoardRequestDto.builder()
                .title("나는 배민 개발자")
                .content("될 거임. 그래서 빡공해야됨")
                .build();

        given(boardFacade.findBoardById(anyLong())).willReturn(board);

        // When
        boardService.updatePost(request, anyLong());

        // Then
        assertEquals("나는 배민 개발자", board.getTitle());
        assertEquals("될 거임. 그래서 빡공해야됨", board.getContent());
    }

    @DisplayName("[Service] 게시물 삭제")
    @Test
    void deletePostTest() {
        // Given
        given(boardFacade.findBoardById(anyLong())).willReturn(board);

        // When
        boardService.deletePost(anyLong());

        // Then
        assertEquals(BoardStatus.DELETED, board.getBoardStatus());
    }

}