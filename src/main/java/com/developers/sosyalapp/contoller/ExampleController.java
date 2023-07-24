package com.developers.sosyalapp.contoller;

import com.developers.sosyalapp.dto.request.CreateExampleRequest;
import com.developers.sosyalapp.dto.response.ApiResponse;
import com.developers.sosyalapp.dto.response.CreateExampleResponse;
import com.developers.sosyalapp.service.ExampleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/example")
public class ExampleController {

    private final ExampleService exampleService;

    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateExampleResponse>> createExample(@RequestBody CreateExampleRequest request) {
        return ResponseEntity.ok(exampleService.createExample(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getExample(@PathVariable String id) {
        return ResponseEntity.ok(exampleService.getExample(id));
    }
}
