package com.bamdoliro.gati.domain.ddo.service;

import com.bamdoliro.gati.domain.community.facade.MemberFacade;
import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.DdoJoin;
import com.bamdoliro.gati.domain.ddo.domain.repository.DdoJoinRepository;
import com.bamdoliro.gati.domain.ddo.facade.DdoFacade;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DdoJoinService {

    private final DdoFacade ddoFacade;
    private final UserFacade userFacade;
    private final DdoJoinRepository ddoJoinRepository;
    private final MemberFacade memberFacade;

    @Transactional
    public void join(Long id) {
        Ddo ddo = ddoFacade.findDdoById(id);
        User user = userFacade.getCurrentUser();

        memberFacade.checkMember(user, ddo.getCommunity());
        ddoFacade.validationJoin(ddo, user);

        ddoJoinRepository.save(DdoJoin.createDoJoin(ddo, user));
    }
}
