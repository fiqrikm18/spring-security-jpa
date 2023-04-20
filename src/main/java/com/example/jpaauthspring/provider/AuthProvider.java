package com.example.jpaauthspring.provider;

import com.example.jpaauthspring.user.UserDetailsServiceImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountLockedException;

@Service
public class AuthProvider implements AuthenticationProvider {

//    final BCryptPasswordEncoder passwordEncoder;
    final UserDetailsServiceImpl userDetailsService;

    public AuthProvider(UserDetailsServiceImpl userDetailsService) {
//        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String username = authentication.getName();
            String password = (String) authentication.getCredentials();

            var user = userDetailsService.loadUserByUsername(username);
//            if (!passwordEncoder.matches(password, user.getPassword())) {
//                throw new BadCredentialsException("invalid username or password");
//            }

            if (!user.isAccountNonExpired()) {
                throw new AccountExpiredException("account expired");
            }

            if (!user.isAccountNonLocked()) {
                throw new AccountLockedException("account suspended");
            }

            if (!user.isCredentialsNonExpired()) {
                throw new CredentialsExpiredException("invalid username or password");
            }

            if (!user.isEnabled()) {
                throw new DisabledException("account suspended");
            }

            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
