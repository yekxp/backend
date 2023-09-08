package com.developers.hireasenior.service;

import com.developers.hireasenior.dto.request.CreateTechnologyRequest;
import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.dto.response.AuthenticationResponse;
import com.developers.hireasenior.dto.response.CreateTechnologyResponse;
import com.developers.hireasenior.exception.ResourceAlreadyExistsException;
import com.developers.hireasenior.model.Technology;
import com.developers.hireasenior.repository.TechnologyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyService {
    private final TechnologyRepository technologyRepository;
    private static final Logger logger = LoggerFactory.getLogger(TechnologyService.class);

    public TechnologyService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    public ApiResponse<CreateTechnologyResponse> addTechnology(CreateTechnologyRequest request){
        try {
            Technology technology = new Technology();
            technology.setName(request.getName());
            technology.setCode(request.getCode());
            technology.setDescription(request.getDescription());
            Technology existTechnology = technologyRepository.findTechnologyByCode(request.getCode());
            if(existTechnology != null){
                throw new ResourceAlreadyExistsException("technology");
            }
            technologyRepository.save(technology);
            logger.info("Technology created successfully: {}", technology.getCode());
            return new ApiResponse<>(true, new CreateTechnologyResponse(technology), "Technology created successfully.");
        } catch (ResourceAlreadyExistsException e) {
            logger.error("Technology already exists: {}", request.getCode());
            return new ApiResponse<>(false, null, e.getMessage());
        } catch (Exception e) {
            logger.error("An unknown error occurred when creating technology: {}", request.getCode());
            return new ApiResponse<>(false, null, "An unknown error occurred when creating technology.");
        }
    }


}
