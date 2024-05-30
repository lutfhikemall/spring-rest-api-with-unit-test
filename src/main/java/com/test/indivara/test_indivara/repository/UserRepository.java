package com.test.indivara.test_indivara.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.test.indivara.test_indivara.constnats.Query;
import com.test.indivara.test_indivara.dto.request.CreateUserRequest;
import com.test.indivara.test_indivara.models.UserModel;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createUser(CreateUserRequest userReq) {
        Object[] queryParam = new Object[] { userReq.getFullName(), userReq.getEmail(), userReq.getPassword() };

        jdbcTemplate.update(Query.User.CREATE, queryParam);
    }

    public Optional<UserModel> findUserByEmail(String email) {
        try {
            Object[] queryParam = new Object[] { email };

            UserModel user = jdbcTemplate.queryForObject(Query.User.FIND_BY_EMAIL, UserModel.userRowMapper(),
                    queryParam);

            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
