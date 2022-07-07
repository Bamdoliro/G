package com.bamdoliro.gati.domain.board.service;

import com.bamdoliro.gati.domain.board.domain.repository.DoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoService {

    private final DoRepository doRepository;

}
