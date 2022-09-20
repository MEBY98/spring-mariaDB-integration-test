package com.example.meby98.testWithMariaDB;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountryRequest {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private Integer population;
}
