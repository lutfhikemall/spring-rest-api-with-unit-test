package com.test.indivara.test_indivara.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProductRequest {

    @NotEmpty(message = "Name is required")
    private String name;
}
