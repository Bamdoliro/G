package com.bamdoliro.gati.domain.ddo.presentation;

import com.bamdoliro.gati.domain.ddo.presentation.dto.request.CreateDdoRequestDto;
import com.bamdoliro.gati.domain.ddo.presentation.dto.response.DdoDetailResponseDto;
import com.bamdoliro.gati.domain.ddo.service.DdoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ddo")
@RequiredArgsConstructor
public class DdoController {

    private final DdoService ddoService;

    @PostMapping
    public void post(@RequestBody CreateDdoRequestDto request) {
        ddoService.savePost(request);
    }

    @GetMapping("/{id}")
    public DdoDetailResponseDto getDoDetail(@PathVariable Long id) {
        return ddoService.getDdoDetail(id);
    }
}
