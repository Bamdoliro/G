package com.bamdoliro.gati.domain.board.presentation;

import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateDoRequestDto;
import com.bamdoliro.gati.domain.board.service.DoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/do")
@RequiredArgsConstructor
public class DoController {

    private final DoService doService;

    @PostMapping
    public void post(
            @RequestBody CreateDoRequestDto request
    ) {
      doService.post(request);
    }

}
