package com.developers.hireasenior.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddTechnologyRequest {
    @NotBlank(message = "Name is required.")
    private String name;
    @NotBlank(message = "Code is required.")
    private String code;
    private String description;

    public AddTechnologyRequest() {
    }

    public AddTechnologyRequest(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }
}
