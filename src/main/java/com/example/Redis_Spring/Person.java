package com.example.Redis_Spring;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person implements Serializable {

    @NotNull
    private Long id;

    private String name;
    private Double height;
    private Integer weight;
    private Boolean seniorCitizen;
}
