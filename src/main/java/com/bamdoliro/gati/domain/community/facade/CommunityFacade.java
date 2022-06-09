package com.bamdoliro.gati.domain.community.facade;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.community.domain.repository.CommunityRepository;
import com.bamdoliro.gati.domain.community.exception.BadPasswordAndCommunityTypeException;
import com.bamdoliro.gati.domain.community.exception.CommunityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommunityFacade {

    private final CommunityRepository communityRepository;

    public Community findCommunityById(Long id) {
        return communityRepository.findById(id)
                .orElseThrow(() -> CommunityNotFoundException.EXCEPTION);
    }

    public Community findCommunityByCode(String code) {
        return communityRepository.findByCode(code)
                .orElseThrow(() -> CommunityNotFoundException.EXCEPTION);
    }

    public boolean checkCode(String code) {
        return !communityRepository.existsByCode(code);
    }

    public void checkPasswordAndCommunityType(String password, Boolean isPublic) {
        if (!((password != null && !isPublic) || (password == null && isPublic))) {
            throw BadPasswordAndCommunityTypeException.EXCEPTION;
        }
    }
}
