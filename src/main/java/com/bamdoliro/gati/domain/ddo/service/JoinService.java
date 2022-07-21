package com.bamdoliro.gati.domain.ddo.service;

import com.bamdoliro.gati.domain.community.facade.MemberFacade;
import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.domain.Join;
import com.bamdoliro.gati.domain.ddo.domain.repository.JoinRepository;
import com.bamdoliro.gati.domain.ddo.facade.DdoFacade;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final DdoFacade ddoFacade;
    private final UserFacade userFacade;
    private final JoinRepository joinRepository;
    private final MemberFacade memberFacade;

    @Transactional
    public void join(Long id) {
        Ddo ddo = ddoFacade.findById(id);
        User user = userFacade.getCurrentUser();

        memberFacade.checkMember(user, ddo.getCommunity());
        ddoFacade.validationJoin(ddo, user);

        joinRepository.save(Join.createDoJoin(ddo, user));
    }
}
