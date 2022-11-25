package com.bamdoliro.gati.domain.ddo.service;

import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.Recommendation;
import com.bamdoliro.gati.domain.ddo.domain.repository.RecommendationRepository;
import com.bamdoliro.gati.domain.ddo.facade.DdoFacade;
import com.bamdoliro.gati.domain.ddo.facade.RecommendationFacade;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final RecommendationFacade recommendationFacade;
    private final DdoFacade ddoFacade;
    private final UserFacade userFacade;


    @Transactional
    public void addRecommendation(Long id) {
        User user = userFacade.getCurrentUser();
        Ddo ddo = ddoFacade.findDdoById(id);

        recommendationFacade.validationRecommendation(user, ddo);

        recommendationRepository.save(Recommendation.createDdo(user, ddo));
    }

    @Transactional
    public void removeRecommendation(Long id) {
        User user = userFacade.getCurrentUser();
        Ddo ddo = ddoFacade.findDdoById(id);
        recommendationRepository.deleteByUserAndDdo(user, ddo);
    }
}
