package com.test.indivara.test_indivara.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.test.indivara.test_indivara.dto.request.CreateUserRequest;
import com.test.indivara.test_indivara.dto.request.LoginUserRequest;
import com.test.indivara.test_indivara.dto.response.CreateUserResponse;
import com.test.indivara.test_indivara.dto.response.LoginUserResponse;
import com.test.indivara.test_indivara.models.UserModel;
import com.test.indivara.test_indivara.repository.UserRepository;
import com.test.indivara.test_indivara.utils.Utilities;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    public CreateUserResponse createUser(CreateUserRequest userRequest) {
        try {
            CreateUserResponse response = new CreateUserResponse();

            String hashPassword = Utilities.hashPassword(userRequest.getPassword());

            userRequest.setPassword(hashPassword);

            userRepo.createUser(userRequest);

            response.setStatus("OK");

            return response;
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User is already exist");
        }
    }

    public LoginUserResponse loginUser(LoginUserRequest userRequest) {
        LoginUserResponse response = new LoginUserResponse();

        Optional<UserModel> user = userRepo.findUserByEmail(userRequest.getEmail());

        if (!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        boolean isValidPassword = Utilities.isValidPassword(userRequest.getPassword(), user.get().getPassword());

        if (!isValidPassword) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        String token = jwtService.generateToken(user.get());

        response.setStatus("OK");
        response.setData(token);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(token,
                        userRequest.getPassword()));

        return response;
    }
}
