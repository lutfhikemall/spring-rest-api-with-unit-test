package com.test.indivara.test_indivara.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    
    @NotEmpty(message = "Full Name is required")
    private String fullName;
    
    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password required minimal 8 length character")
    private String password;
}
