package com.oxygen.oxygenspring.domain.user.service;

import com.oxygen.oxygenspring._common.exception.ApiException;
import com.oxygen.oxygenspring._common.exception.responseCode.ResponseCode;
import com.oxygen.oxygenspring.db.entity.Users;
import com.oxygen.oxygenspring.db.repository.UsersRepository;
import com.oxygen.oxygenspring.domain.user.dto.UserReqDto;
import com.oxygen.oxygenspring.domain.user.dto.UserSignUpReqDto;
import com.oxygen.oxygenspring.domain.user.jwt.provider.JwtAuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @Transactional
    public void signUp(UserSignUpReqDto requestDto) {
        usersRepository.findByUserId(requestDto.getUserId())
                .ifPresent(user -> {
                    throw new ApiException(ResponseCode.EXIST_USER);
                });

        usersRepository.save(Users.builder()
                .userId(requestDto.getUserId())
                .userPw(passwordEncoder.encode(requestDto.getUserPw()))
                .userNickname(requestDto.getUserNickname())
                .build());
    }

    public String login(UserReqDto requestDto) {

        Users user = usersRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(() -> new ApiException(ResponseCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(requestDto.getUserPw(), user.getUserPw())) {
            throw new ApiException(ResponseCode.INVALID_PASSWORD);
        }

        return jwtAuthTokenProvider.createJwtAuthToken(requestDto.getUserId()).getToken();
    }
}
