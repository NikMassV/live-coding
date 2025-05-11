package org.example.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(

        @NotBlank(message = "Name must not be blank")
        String name,
        @NotBlank(message = "Password must not be blank")
        String password) {
}
