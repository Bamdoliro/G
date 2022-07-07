package com.bamdoliro.gati.domain.board.presentation;

import com.bamdoliro.gati.domain.board.service.DoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/do")
@RequiredArgsConstructor
public class DoController {

    private final DoService doService;

}
