package com.bamdoliro.gati.domain.ddo.facade;

import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.repository.RecommendationRepository;
import com.bamdoliro.gati.domain.ddo.exception.AlreadyRecommendException;
import com.bamdoliro.gati.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecommendationFacade {

    private final RecommendationRepository recommendationRepository;

    public void validationRecommendation(User user, Ddo ddo) {
        if (recommendationRepository.existsByUserAndDdo(user, ddo)) {
            throw AlreadyRecommendException.EXCEPTION;
        }
    }
}
