package com.bamdoliro.gati.domain.ddo.presentation;

import com.bamdoliro.gati.domain.ddo.presentation.dto.request.CreateDdoRequestDto;
import com.bamdoliro.gati.domain.ddo.presentation.dto.response.DdoDetailResponseDto;
import com.bamdoliro.gati.domain.ddo.presentation.dto.response.DdoResponseDto;
import com.bamdoliro.gati.domain.ddo.service.DdoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/do")
@RequiredArgsConstructor
public class DdoController {

    private final DdoService ddoService;

    @PostMapping
    public void post(
            @RequestBody CreateDdoRequestDto request
    ) {
        ddoService.post(request);
    }

    @GetMapping("/{id}")
    public DdoDetailResponseDto getDoDetail(
            @PathVariable(name = "id") Long id
    ) {
        return ddoService.getDoDetail(id);
    }
    
}
