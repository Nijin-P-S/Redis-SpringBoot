package com.example.Redis_Spring;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    private Long id;
    private String name;
    private Double height;
    private Integer weight;
    private Boolean seniorCitizen;
}
