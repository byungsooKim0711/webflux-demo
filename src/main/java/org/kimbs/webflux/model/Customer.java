package org.kimbs.webflux.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Long id;

    private String firstname;

    private String lastname;

    @Min(value = 1)
    @Max(value = 99)
    private int age;
}