package com.developers.hireasenior.dto.response;


import com.developers.hireasenior.model.Technology;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteTechnologyResponse {
    private Technology technology;
}
