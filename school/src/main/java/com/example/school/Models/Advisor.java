package com.example.school.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @Data
public class Advisor {
    @NotEmpty(message = "id cant be empty")
    private String id;
    @NotEmpty(message = "name cant be empty")
    private String name;
    @NotNull(message = "age cant be empty")
    private Integer age;

}
