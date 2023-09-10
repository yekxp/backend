package com.developers.hireasenior.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddLanguageRequest {
    @NotBlank(message = "Name is required.")
    private String name;
    @NotBlank(message = "Code is required.")
    private String code;
}

