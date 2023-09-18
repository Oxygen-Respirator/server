package com.oxygen.oxygenspring.domain.user.details;

import com.oxygen.oxygenspring.db.entity.Users;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class UserDetailsImpl implements UserDetails {

    private final String userId;
    private final String userNickname;
    private final String userPw;

    @Builder
    public UserDetailsImpl(String userId, String userNickname, String userPw) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userPw = userPw;
    }

    public static UserDetailsImpl create(Users users) {
        return UserDetailsImpl.builder()
                .userId(users.getUserId())
                .userNickname(users.getUserNickname())
                .userPw(users.getUserPw())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return userPw;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
