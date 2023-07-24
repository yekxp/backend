package com.developers.sosyalapp.dto;

public class ExampleDto {
    private String id;
    private String name;

    public ExampleDto() {
    }

    public ExampleDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public ExampleDto(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public ExampleDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ExampleDto setName(String name) {
        this.name = name;
        return this;
    }
}
