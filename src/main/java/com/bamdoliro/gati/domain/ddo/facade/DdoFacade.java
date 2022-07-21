package com.bamdoliro.gati.domain.ddo.facade;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.repository.JoinRepository;
import com.bamdoliro.gati.domain.ddo.domain.repository.DdoRepository;
import com.bamdoliro.gati.domain.ddo.domain.type.ddo.Status;
import com.bamdoliro.gati.domain.ddo.exception.AlreadyJoinException;
import com.bamdoliro.gati.domain.board.exception.BoardNotFoundException;
import com.bamdoliro.gati.domain.ddo.exception.DdoNotFoundException;
import com.bamdoliro.gati.domain.ddo.presentation.dto.response.DdoResponseDto;
import com.bamdoliro.gati.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DdoFacade {

    private final DdoRepository ddoRepository;
    private final JoinRepository ddoJoinRepository;

    public Ddo findById(Long id) {
        return ddoRepository.findById(id)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);
    }
    
}
