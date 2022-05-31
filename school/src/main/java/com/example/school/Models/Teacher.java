package com.example.school.Models;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Data
public class Teacher {
    //
    @NotEmpty(message = "id cant be empty")
    private String id;
    @NotEmpty(message = "name cant be empty")
    private String name;

    private ArrayList<Classes> classList;

    public Teacher(String id, String name ) {
        this.id = id;
        this.name = name;
        this.classList = new ArrayList<>();
    }
}
