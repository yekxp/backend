package com.developers.hireasenior.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyDto {
    @NotBlank(message = "Name is required.")
    private String name;
    @NotBlank(message = "Code is required.")
    private String code;
    private String description;
}
