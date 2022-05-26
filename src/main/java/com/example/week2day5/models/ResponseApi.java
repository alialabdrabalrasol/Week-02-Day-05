package com.example.week2day5.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class ResponseApi {
    private String message;
    private Integer status;
}
