package com.bamdoliro.gati.domain.ddo.presentation;

import com.bamdoliro.gati.domain.ddo.presentation.dto.response.UserResponseDto;
import com.bamdoliro.gati.domain.ddo.service.DdoJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ddo/{id}/join")
@RequiredArgsConstructor
public class DdoJoinController {

    private final DdoJoinService ddoJoinService;

    @PostMapping
    public void join(@PathVariable Long id) {
        ddoJoinService.ddoJoin(id);
    }

    @DeleteMapping
    public void cancelJoin(@PathVariable Long id) {
        ddoJoinService.cancelDdoJoin(id);
    }

    @GetMapping
    public List<UserResponseDto> findJoiners(@PathVariable Long id) {
        return ddoJoinService.findJoiners(id);
    }
}
