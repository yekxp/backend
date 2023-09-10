package com.developers.hireasenior.dto.response;

import com.developers.hireasenior.model.Technology;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListTechnologiesResponse {
    private List<Technology> technologies;
}
