package com.oxygen.oxygenspring.domain.user.service;

import com.oxygen.oxygenspring.db.entity.Users;
import com.oxygen.oxygenspring.db.repository.UsersRepository;
import com.oxygen.oxygenspring.domain.user.details.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Users> users = usersRepository.findByUserId(userId);

        if (users.isPresent()) {
            Users user = users.get();
            return UserDetailsImpl.create(user);
        }

        throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
    }
}
