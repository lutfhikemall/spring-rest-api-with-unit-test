package com.test.indivara.test_indivara.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.test.indivara.test_indivara.models.UserModel;
import com.test.indivara.test_indivara.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String token) throws ResponseStatusException {
        if (token.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token is required");
        }

        String email = jwtService.extractEmail(token);

        if (email.isEmpty()) {
            log.info("Email is empty");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token");
        }

        Optional<UserModel> user = userRepo.findUserByEmail(email);

        if (!user.isPresent()) {
            log.info("User is not found");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token");
        }

        boolean isValidToken = jwtService.isTokenValid(token, email);

        if (!isValidToken) {
            log.info("Token is invalid");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token");
        }

        log.info("User exist");

        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("USER");

        return org.springframework.security.core.userdetails.User
                .withUsername(user.get().getEmail())
                .password(user.get().getPassword())
                .roles("USER")
                .authorities(auths)
                .disabled(false)
                .accountExpired(false)
                .accountLocked(false)
                .build();
    }

}
