package com.bamdoliro.gati.domain.ddo.presentation;

import com.bamdoliro.gati.domain.ddo.presentation.dto.request.CreateDdoRequestDto;
import com.bamdoliro.gati.domain.ddo.presentation.dto.response.DdoDetailResponseDto;
import com.bamdoliro.gati.domain.ddo.presentation.dto.response.DdoResponseDto;
import com.bamdoliro.gati.domain.ddo.service.DdoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ddo")
@RequiredArgsConstructor
public class DdoController {

    private final DdoService ddoService;

    @PostMapping
    public void post(@RequestBody CreateDdoRequestDto request) {
        ddoService.savePost(request);
    }

    @GetMapping("/community/{communityId}")
    public List<DdoResponseDto> getDdoOrderByRecommendation(
            @PathVariable Long communityId
    ) {
        return ddoService.findDdoOrderByRecommendationSize(communityId);
    }

    @GetMapping("/{id}")
    public DdoDetailResponseDto getDoDetail(@PathVariable Long id) {
        return ddoService.getDdoDetail(id);
    }
}
