package com.bamdoliro.gati.domain.ddo.presentation;

import com.bamdoliro.gati.domain.ddo.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ddo/join")
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/{id}")
    public void join(
            @PathVariable(name = "id") Long id
    ) {
        joinService.join(id);
    }
}
