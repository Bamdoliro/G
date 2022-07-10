package com.bamdoliro.gati.domain.board.presentation;

import com.bamdoliro.gati.domain.board.presentation.dto.request.CreateDoRequestDto;
import com.bamdoliro.gati.domain.board.presentation.dto.response.DoDetailResponseDto;
import com.bamdoliro.gati.domain.board.service.DoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public DoDetailResponseDto getDoDetail(
            @PathVariable(name = "id") Long id
    ) {
        return doService.getDoDetail(id);
    }

}
