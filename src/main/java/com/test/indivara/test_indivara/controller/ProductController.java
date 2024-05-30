package com.test.indivara.test_indivara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.indivara.test_indivara.dto.request.CreateProductRequest;
import com.test.indivara.test_indivara.dto.request.UpdateProductRequest;
import com.test.indivara.test_indivara.dto.response.CreateProductResponse;
import com.test.indivara.test_indivara.dto.response.DeleteProductResponse;
import com.test.indivara.test_indivara.dto.response.GetProductResponse;
import com.test.indivara.test_indivara.dto.response.UpdateProductResponse;
import com.test.indivara.test_indivara.services.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public CreateProductResponse createProduct(@Valid @RequestBody CreateProductRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping("/{id}")
    public UpdateProductResponse updateProduct(@Valid @RequestBody UpdateProductRequest request,
            @PathVariable Integer id) {
        return productService.updateProduct(request, id);
    }

    @GetMapping
    public GetProductResponse getProduct() {
        return productService.getAllProduct();
    }

    @DeleteMapping("/{id}")
    public DeleteProductResponse deleteProduct(@Valid @PathVariable("id") @NotEmpty Integer id) {
        return productService.deleteProduct(id);
    }

}
