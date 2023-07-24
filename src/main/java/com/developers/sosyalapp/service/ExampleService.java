package com.developers.sosyalapp.service;

import com.developers.sosyalapp.dto.request.CreateExampleRequest;
import com.developers.sosyalapp.dto.response.ApiResponse;
import com.developers.sosyalapp.dto.response.CreateExampleResponse;
import com.developers.sosyalapp.exception.ExampleNotFoundException;
import com.developers.sosyalapp.mapper.ExampleMapper;
import com.developers.sosyalapp.model.Example;
import com.developers.sosyalapp.repository.ExampleRepository;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    private final ExampleMapper exampleMapper;
    private final ExampleRepository exampleRepository;

    public ExampleService(ExampleRepository exampleRepository, ExampleMapper exampleMapper) {
        this.exampleRepository = exampleRepository;
        this.exampleMapper = exampleMapper;
    }

    public ApiResponse<CreateExampleResponse> createExample(CreateExampleRequest request) {
        try {
            Example example = exampleRepository.save(exampleMapper.toEntity(request.getExample()));
            return new ApiResponse<>(true, new CreateExampleResponse(exampleMapper.toDto(example)), "Example created successfully.");
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "Example could not be created.");
        }
    }

    public ApiResponse<CreateExampleResponse> getExample(String id) {
        try {
            Example example = exampleRepository.findById(id).orElse(null);
            if(example == null) {
                throw new ExampleNotFoundException("Example not found with id " + id);
            }
            return new ApiResponse<>(true, new CreateExampleResponse(exampleMapper.toDto(example)), "Example retrieved successfully");
        } catch (ExampleNotFoundException e) {
            return new ApiResponse<>(false, null, e.getMessage());
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "Unexpected error occurred while getting exception.");
        }
    }
}
