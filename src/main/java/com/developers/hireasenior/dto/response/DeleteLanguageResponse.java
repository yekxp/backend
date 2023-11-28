package com.developers.hireasenior.dto.response;

import com.developers.hireasenior.model.Language;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteLanguageResponse {
    private final Language language;
}
