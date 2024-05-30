package com.test.indivara.test_indivara.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.indivara.test_indivara.constnats.Query;
import com.test.indivara.test_indivara.dto.request.CreateProductRequest;
import com.test.indivara.test_indivara.dto.request.UpdateProductRequest;
import com.test.indivara.test_indivara.models.ProductModel;

@Repository
public class ProductRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createProduct(CreateProductRequest productReq) {
        Object[] queryParam = new Object[] { productReq.getName() };

        jdbcTemplate.update(Query.Product.CREATE, queryParam);
    }

    @Transactional
    public void updateProduct(UpdateProductRequest productReq, Integer id) {
        Object[] queryParam = new Object[] { productReq.getName(), id };

        jdbcTemplate.update(Query.Product.UPDATE, queryParam);
    }

    public List<ProductModel> viewProduct() {
        List<ProductModel> products = jdbcTemplate.query(Query.Product.VIEW, ProductModel.productRowMapper());

        return products;
    }

    @Transactional
    public void deleteProduct(Integer id) {
        Object[] queryParam = new Object[] { id };

        jdbcTemplate.update(Query.Product.DELETE, queryParam);
    }
}
