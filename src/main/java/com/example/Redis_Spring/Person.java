package com.example.Redis_Spring;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    @NotNull
    private Long id;

    private String name;
    private Double height;
    private Integer weight;
    private Boolean seniorCitizen;
}
