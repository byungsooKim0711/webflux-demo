package org.kimbs.webflux.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Long id;

    @Length(min = 1, max = 20, message = "should be length between 1 and 20")
    private String firstname;

    @Length(min = 1, max = 20, message = "should be length between 1 and 20")
    private String lastname;

    @Min(value = 1, message = "should be greater than equal to 1")
    @Max(value = 99, message = "should be less than equal to 99")
    private int age;
}