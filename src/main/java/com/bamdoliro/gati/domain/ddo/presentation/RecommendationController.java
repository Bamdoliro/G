package com.bamdoliro.gati.domain.ddo.presentation;

import com.bamdoliro.gati.domain.ddo.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ddo/{ddoId}/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping
    public void recommendationAdd(
            @PathVariable Long ddoId
    ) {
        recommendationService.addRecommendation(ddoId);
    }

    @DeleteMapping
    public void recommendationRemove(
            @PathVariable Long ddoId
    ) {
        recommendationService.removeRecommendation(ddoId);
    }
}
