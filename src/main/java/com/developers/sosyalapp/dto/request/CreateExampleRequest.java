package com.developers.sosyalapp.dto.request;

import com.developers.sosyalapp.dto.ExampleDto;

public class CreateExampleRequest {
    private ExampleDto example;

    public ExampleDto getExample() {
        return example;
    }

    public void setExample(ExampleDto example) {
        this.example = example;
    }
}
