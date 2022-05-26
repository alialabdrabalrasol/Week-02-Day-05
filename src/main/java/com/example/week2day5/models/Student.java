package com.example.week2day5.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Data
public class Student {
    @NotEmpty(message = "id cant be empty")
    private String id;
    @NotEmpty(message = "name cant be empty")
    private String name;
    @NotNull(message = "age cant be empty")
    private Integer age;
    @NotEmpty(message = "advisor name cant be empty")
    private String advisorName;
    @NotEmpty(message = "major cant be empty")
    private String major;
    private ArrayList<Classes>classList;

    public Student(String id, String name, Integer age, String advisorName, String major ) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.advisorName = advisorName;
        this.major = major;
        this.classList = new ArrayList<>();
    }
}
