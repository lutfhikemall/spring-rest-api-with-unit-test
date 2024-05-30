package com.test.indivara.test_indivara.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.indivara.test_indivara.dto.request.CreateProductRequest;
import com.test.indivara.test_indivara.dto.request.UpdateProductRequest;
import com.test.indivara.test_indivara.dto.response.CreateProductResponse;
import com.test.indivara.test_indivara.dto.response.DeleteProductResponse;
import com.test.indivara.test_indivara.dto.response.GetProductResponse;
import com.test.indivara.test_indivara.dto.response.UpdateProductResponse;
import com.test.indivara.test_indivara.models.ProductModel;
import com.test.indivara.test_indivara.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepo;

    public CreateProductResponse createProduct(CreateProductRequest productReq) {
        CreateProductResponse response = new CreateProductResponse();

        productRepo.createProduct(productReq);

        response.setStatus("OK");

        return response;
    }

    public UpdateProductResponse updateProduct(UpdateProductRequest productReq, Integer id) {
        UpdateProductResponse response = new UpdateProductResponse();

        productRepo.updateProduct(productReq, id);

        response.setStatus("OK");

        return response;
    }

    public GetProductResponse getAllProduct() {
        GetProductResponse response = new GetProductResponse();

        List<ProductModel> products = productRepo.viewProduct();

        response.setStatus("OK");
        response.setData(products);

        return response;
    }

    public DeleteProductResponse deleteProduct(Integer id) {
        DeleteProductResponse response = new DeleteProductResponse();

        productRepo.deleteProduct(id);

        response.setStatus("OK");

        return response;
    }
}
