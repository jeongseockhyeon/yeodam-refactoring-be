package com.auth.entity.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN","관리자"),
    USER("ROLE_USER","일반 회원"),
    SELLER("ROLE_SELLER","판매 회원"),
    NONE("ROLE_NONE","탈퇴 회원");

    private final String key;
    private final String value;
}
