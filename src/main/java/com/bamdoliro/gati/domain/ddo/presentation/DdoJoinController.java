package com.bamdoliro.gati.domain.ddo.presentation;

import com.bamdoliro.gati.domain.ddo.presentation.dto.response.UserResponseDto;
import com.bamdoliro.gati.domain.ddo.service.DdoJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ddo/{id}/join")
@RequiredArgsConstructor
public class DdoJoinController {

    private final DdoJoinService ddoJoinService;

    @PostMapping
    public void join(
            @PathVariable(name = "id") Long id
    ) {
        ddoJoinService.ddoJoin(id);
    }

    @GetMapping
    public List<UserResponseDto> findJoiners(
            @PathVariable(name = "id") Long id
    ) {
        return ddoJoinService.findJoiners(id);
    }
}
