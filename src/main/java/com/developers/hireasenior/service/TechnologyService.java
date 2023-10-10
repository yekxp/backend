package com.developers.hireasenior.service;

import com.developers.hireasenior.dto.request.AddTechnologyRequest;
import com.developers.hireasenior.dto.request.DeleteTechnologyRequest;
import com.developers.hireasenior.dto.request.UpdateTechnologyRequest;
import com.developers.hireasenior.dto.response.*;
import com.developers.hireasenior.exception.ResourceAlreadyExistsException;
import com.developers.hireasenior.exception.ResourceNotFoundException;
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
            technology.setImageUrl(request.getImageUrl());
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
            return new ApiResponse<>(true, new ListTechnologiesResponse(technologies), "Technologies successfully listed.", false);
        } catch (Exception e) {
            logger.error("An unknown error occurred when listing technologies: {}", e.getMessage());
            return new ApiResponse<>(false, null, "An unknown error occurred when listing technologies.", false);
        }
    }

    public ApiResponse<UpdateTechnologyResponse> updateTechnology(UpdateTechnologyRequest request) {
        try {
            Technology existTechnology = technologyRepository.findTechnologyByCode(request.getCode());
            if (existTechnology == null) {
                throw new ResourceNotFoundException("Technology not found");
            }
            existTechnology.setName(request.getName());
            existTechnology.setDescription(request.getDescription());
            if(request.getImageUrl() != null){
                existTechnology.setImageUrl(request.getImageUrl());
            }
            technologyRepository.save(existTechnology);
            logger.info("Technology updated successfully: {}", existTechnology.getCode());
            return new ApiResponse<>(true, new UpdateTechnologyResponse(existTechnology), "Technology updated successfully.");
        }catch (ResourceNotFoundException e){
            logger.error("Technology not found", request.getCode());
            return new ApiResponse<>(false, null, e.getMessage());
        }catch (Exception e) {
            logger.error("An unknown error occurred when updating technology: {}", request.getCode());
            return new ApiResponse<>(false, null, "An unknown error occurred when updating technology.");
        }
    }

    public ApiResponse<DeleteTechnologyResponse> deleteTechnology(DeleteTechnologyRequest request){
        try {
            Technology technology = technologyRepository.findTechnologyByCode(request.getCode());
            if (technology == null) {
                throw new ResourceNotFoundException("Technology not found");
            }technologyRepository.delete(technology);
            logger.info("Technology deleted successfully: {}", technology.getCode());
            return new ApiResponse<>(true, new DeleteTechnologyResponse(technology), "Technology deleted successfully.");
        } catch (ResourceNotFoundException e) {
            logger.error("Technology not found for deletion: {}", request.getCode());
            return new ApiResponse<>(false, null, e.getMessage());
        } catch (Exception e) {
            logger.error("An unknown error occurred when deleting technology: {}", request.getCode());
            return new ApiResponse<>(false, null, "An unknown error occurred when deleting technology.");
        }

    }
}
