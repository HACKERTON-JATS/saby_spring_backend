package com.jats.savy.savy.security.auth;

import com.jats.savy.savy.entity.admin.AdminRepository;
import com.jats.savy.savy.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public AuthDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map
        return adminRepository.findById(username)
                .map(AuthDetails::new)
                .orElseThrow(UserNotFoundException::new);
    }
}
