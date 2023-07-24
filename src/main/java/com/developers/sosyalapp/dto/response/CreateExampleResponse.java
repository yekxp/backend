package com.developers.sosyalapp.dto.response;

import com.developers.sosyalapp.dto.ExampleDto;

public class CreateExampleResponse {
    private ExampleDto example;

    public CreateExampleResponse(ExampleDto example) {
        this.example = example;
    }

    public ExampleDto getExample() {
        return example;
    }

    public void setExample(ExampleDto example) {
        this.example = example;
    }
}
