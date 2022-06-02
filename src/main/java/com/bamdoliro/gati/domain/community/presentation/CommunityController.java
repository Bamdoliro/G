package com.bamdoliro.gati.domain.community.presentation;

import com.bamdoliro.gati.domain.community.presentation.dto.request.CreateCommunityRequestDto;
import com.bamdoliro.gati.domain.community.presentation.dto.response.CommunityDetailResponseDto;
import com.bamdoliro.gati.domain.community.presentation.dto.response.CommunityResponseDto;
import com.bamdoliro.gati.domain.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping
    public Page<CommunityResponseDto> getPagingCommunity(Pageable pageable) {
        return communityService.getPagingCommunity(pageable);
    }

    @GetMapping("/{id}")
    public CommunityDetailResponseDto getCommunityDetail(@PathVariable Long id) {
        return communityService.getCommunityDetail(id);
    }

    @PostMapping
    public void createCommunity(@RequestBody @Valid CreateCommunityRequestDto dto) {
        communityService.createCommunity(dto);
    }
}
