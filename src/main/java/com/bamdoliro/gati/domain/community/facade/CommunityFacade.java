package com.bamdoliro.gati.domain.community.facade;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.repository.CommunityRepository;
import com.bamdoliro.gati.domain.community.domain.type.CommunityStatus;
import com.bamdoliro.gati.domain.community.exception.BadPasswordAndCommunityTypeException;
import com.bamdoliro.gati.domain.community.exception.CannotDeleteCommunityException;
import com.bamdoliro.gati.domain.community.exception.CommunityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommunityFacade {

    private final CommunityRepository communityRepository;

    public Community findCommunityById(Long id) {
        return communityRepository.findByIdAndCommunityStatus(id, CommunityStatus.EXISTED)
                .orElseThrow(() -> CommunityNotFoundException.EXCEPTION);
    }

    public Community findCommunityByCode(String code) {
        return communityRepository.findByCodeAndCommunityStatus(code, CommunityStatus.EXISTED)
                .orElseThrow(() -> CommunityNotFoundException.EXCEPTION);
    }

    public boolean checkCode(String code) {
        return !communityRepository.existsByCodeAndCommunityStatus(code, CommunityStatus.EXISTED);
    }

    public void checkPasswordAndCommunityType(String password, Boolean isPublic) {
        if (!((password != null && !isPublic) || (password == null && isPublic))) {
            throw BadPasswordAndCommunityTypeException.EXCEPTION;
        }
    }

    public void checkNumberOfMembers(Community community, Integer max) {
        if (!(community.getMembers().size() <= max)) {
            throw CannotDeleteCommunityException.EXCEPTION;
        }
    }

    public void checkCommunityExists(Long communityId) {
        if (!communityRepository.existsById(communityId)) {
            throw CommunityNotFoundException.EXCEPTION;
        }
    }
}
