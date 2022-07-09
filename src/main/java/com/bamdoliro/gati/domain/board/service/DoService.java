package com.bamdoliro.gati.domain.board.service;

import com.bamdoliro.gati.domain.board.domain.DoPost;
import com.bamdoliro.gati.domain.board.domain.repository.DoRepository;
import com.bamdoliro.gati.domain.board.facade.DoFacade;
import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateDoRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.DoDetailResponseDto;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DoService {

    private final DoRepository doRepository;
    private final UserFacade userFacade;
    private final DoFacade doFacade;

    @Transactional
    public void post(CreateDoRequestDto request) {
        User user = userFacade.getCurrentUser();
        DoPost doPost = request.toEntity();
        doPost.setRelation(user);

        doRepository.save(doPost);
    }

    @Transactional(readOnly = true)
    public DoDetailResponseDto getDoDetail(Long id) {
        return DoDetailResponseDto.of(doFacade.findById(id));
    }
}
