package org.kimbs.webflux.unittests;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kimbs.webflux.model.Customer;

public class CustomerValidationTests {

    private static Validator validator;

    @BeforeClass
    public static void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void test() throws Exception {
        Customer customer = new Customer();
        customer.setId(1000L);
        customer.setFirstname("Byungsoo");
        customer.setLastname("Kim");
        customer.setAge(100);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(1, violations.size());
    }
}