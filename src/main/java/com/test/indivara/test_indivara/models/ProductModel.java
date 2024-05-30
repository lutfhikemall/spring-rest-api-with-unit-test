package com.test.indivara.test_indivara.models;

import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductModel {
    private Integer id;
    private String name;
    private Date createdAt;
    private Date updatedAt;

    public static RowMapper<ProductModel> productRowMapper() {
        return (rs, rowNum) -> {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            Date createdAt = rs.getDate("created_at");
            Date updatedAt = rs.getDate("updated_at");

            return new ProductModel(id, name, createdAt, updatedAt);
        };
    }
}
