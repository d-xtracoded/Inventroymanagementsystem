package com.prime.InventroryMgtSystem.security;


import com.prime.InventroryMgtSystem.exceptions.NotFoundExecption;
import com.prime.InventroryMgtSystem.models.User;
import com.prime.InventroryMgtSystem.reposit.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(()-> new NotFoundExecption("Username not found"));

        return AuthUser.builder()
                .user(user)
                .build();
    }
}
