package com.bamdoliro.gati.domain.community.presentation;

import com.bamdoliro.gati.domain.community.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/community/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public void joinCommunity(@RequestParam(name = "community") Long communityId) {
        memberService.joinCommunity(communityId);
    }
}
