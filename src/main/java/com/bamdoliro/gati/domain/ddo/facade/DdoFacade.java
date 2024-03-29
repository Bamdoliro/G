package com.bamdoliro.gati.domain.ddo.facade;

import com.bamdoliro.gati.domain.board.exception.BoardNotFoundException;
import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.repository.DdoRepository;
import com.bamdoliro.gati.domain.ddo.domain.repository.DdoJoinRepository;
import com.bamdoliro.gati.domain.ddo.domain.type.DdoStatus;
import com.bamdoliro.gati.domain.ddo.exception.AlreadyJoinException;
import com.bamdoliro.gati.domain.ddo.exception.DdoNotFoundException;
import com.bamdoliro.gati.domain.ddo.exception.ExcessOfCapacityException;
import com.bamdoliro.gati.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DdoFacade {

    private final DdoRepository ddoRepository;
    private final DdoJoinRepository ddoJoinRepository;

    public Ddo findDdoById(Long id) {
        return ddoRepository.findById(id)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);
    }

    public void validationJoin(Ddo ddo, User user) {
        if (ddoJoinRepository.existsByDdoAndJoiner(ddo, user)) {
            throw AlreadyJoinException.EXCEPTION;
        }
        if (ddo.getDdoJoinList().size() >= ddo.getCapacity()) {
            throw ExcessOfCapacityException.EXCEPTION;
        }
    }

    public List<Ddo> findDdoOrderByRecommendationSize(Long communityId) {
        return ddoRepository.findAllOrderByRecommendation(communityId, DdoStatus.OPEN)
                .orElseThrow(() -> DdoNotFoundException.EXCEPTION);
    }
}
