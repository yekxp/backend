package com.developers.hireasenior.dto.request;

import com.developers.hireasenior.dto.TechnologyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyAndPeriodRequest {
    Set<TechnologyDto> technologyDtoSet;
    Date startedAt;
    Date endedAt;
}
