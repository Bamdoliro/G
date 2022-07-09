package com.bamdoliro.gati.domain.board.facade;

import com.bamdoliro.gati.domain.board.domain.DoPost;
import com.bamdoliro.gati.domain.board.domain.repository.DoRepository;
import com.bamdoliro.gati.domain.board.exception.BoardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoFacade {

    private final DoRepository doRepository;

    public DoPost findById(Long id) {
        return doRepository.findById(id)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);
    }
}
