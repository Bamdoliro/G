package com.bamdoliro.gati.domain.community.exception.error;

import com.bamdoliro.gati.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommunityErrorProperty implements ErrorProperty {
    COMMUNITY_NOT_FOUND(404, "공동체를 찾을 수 없습니다."),
    COMMUNITY_PASSWORD_MISMATCH(422, "커뮤니티 비밀번호가 틀렸습니다."),
    USER_NOT_COMMUNITY_MEMBER(422, "사용자가 해당 커뮤니티 멤버가 아닙니다."),
    MEMBER_NOT_FOUND(404, "멤버를 찾을 수 없습니다."),
    AUTHORITY_MISMATCH(403, "권한이 없습니다."),
    CANNOT_DELETE_COMMUNITY(422, "멤버가 2 명 이하일 때만 삭제할 수 있습니다.");

    private final int status;
    private final String message;
}