package com.developers.hireasenior.service;

import com.developers.hireasenior.dto.request.AddTechnologyRequest;
import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.dto.response.AddTechnologyResponse;
import com.developers.hireasenior.dto.response.ListTechnologiesResponse;
import com.developers.hireasenior.exception.ResourceAlreadyExistsException;
import com.developers.hireasenior.model.Technology;
import com.developers.hireasenior.repository.TechnologyRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TechnologyService {
    private final TechnologyRepository technologyRepository;
    private static final Logger logger = LoggerFactory.getLogger(TechnologyService.class);

    public ApiResponse<AddTechnologyResponse> addTechnology(AddTechnologyRequest request){
        try {
            Technology existTechnology = technologyRepository.findTechnologyByCode(request.getCode());
            if(existTechnology != null){
                throw new ResourceAlreadyExistsException("technology");
            }

            Technology technology = new Technology();
            technology.setName(request.getName());
            technology.setCode(request.getCode());
            technology.setDescription(request.getDescription());
            technologyRepository.save(technology);
            logger.info("Technology created successfully: {}", technology.getCode());
            return new ApiResponse<>(true, new AddTechnologyResponse(technology), "Technology created successfully.");
        } catch (ResourceAlreadyExistsException e) {
            logger.error("Technology already exists: {}", request.getCode());
            return new ApiResponse<>(false, null, e.getMessage());
        } catch (Exception e) {
            logger.error("An unknown error occurred when creating technology: {}", request.getCode());
            return new ApiResponse<>(false, null, "An unknown error occurred when creating technology.");
        }
    }

    public ApiResponse<ListTechnologiesResponse> listTechnologies() {
        try {
            List<Technology> technologies = technologyRepository.findAll();
            logger.info("Technologies found: {}", technologies.size());
            return new ApiResponse<>(true, new ListTechnologiesResponse(technologies), "Technologies successfully listed.");
        } catch (Exception e) {
            logger.error("An unknown error occurred when listing technologies: {}", e.getMessage());
            return new ApiResponse<>(false, null, "An unknown error occurred when listing technologies.");
        }
    }
}
