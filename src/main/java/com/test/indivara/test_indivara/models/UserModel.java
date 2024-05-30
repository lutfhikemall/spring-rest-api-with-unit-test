package com.test.indivara.test_indivara.models;

import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {
    private Integer id;
    private String fullName;
    private String email;
    private String password;
    private Date createdAt;
    private Date updatedAt;

    public static RowMapper<UserModel> userRowMapper() {
        return (rs, rowNum) -> {
            Integer id = rs.getInt("id");
            String fullName = rs.getString("full_name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            Date createdAt = rs.getDate("created_at");
            Date updatedAt = rs.getDate("updated_at");

            return new UserModel(id, fullName, email, password, createdAt, updatedAt);
        };
    }
}
