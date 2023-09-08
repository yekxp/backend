package com.developers.hireasenior.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTechnologyRequest {
    private String name;
    private String code;
    private String description;
}
