package org.kimbs.webflux.unittests;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kimbs.webflux.model.Customer;

public class CustomerValidationTests {
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeClass
    public static void setUp() throws Exception {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterClass
    public static void tearDown() {
        factory.close();
    }

    @Test
    public void test1() throws Exception {
        Customer customer = new Customer();
        customer.setId(1000L);
        customer.setFirstname("Byungsoo");
        customer.setLastname("Kim");
        customer.setAge(100);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(1, violations.size());

        String message = validator.validateProperty(customer, "age").iterator().next().getMessage();
        assertEquals("should be less than equal to 99", message);
    }

    @Test
    public void test2() throws Exception {
        Customer customer = new Customer();
        customer.setId(1000L);
        customer.setFirstname("Byungsoo");
        customer.setLastname("Kim");
        customer.setAge(-100);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(1, violations.size());

        String message = validator.validateProperty(customer, "age").iterator().next().getMessage();
        assertEquals("should be greater than equal to 1", message);
    }

    @Test
    public void test3() throws Exception {
        Customer customer = new Customer();
        customer.setId(1000L);
        customer.setFirstname("12345678901234567890++");
        customer.setLastname("12345678901234567890++");
        customer.setAge(-100);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(3, violations.size());

        String message = validator.validateProperty(customer, "firstname").iterator().next().getMessage();
        assertEquals("should be length between 1 and 20", message);

        message = validator.validateProperty(customer, "lastname").iterator().next().getMessage();
        assertEquals("should be length between 1 and 20", message);

        message = validator.validateProperty(customer, "age").iterator().next().getMessage();
        assertEquals("should be greater than equal to 1", message);
    }

    @Test
    public void test4() throws Exception {
        Customer customer = new Customer();
        customer.setId(1000L);
        customer.setFirstname("");
        customer.setLastname("test");
        customer.setAge(1000);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(2, violations.size());

        String message = validator.validateProperty(customer, "firstname").iterator().next().getMessage();
        assertEquals("should be length between 1 and 20", message);

        message = validator.validateProperty(customer, "age").iterator().next().getMessage();
        assertEquals("should be less than equal to 99", message);
    }
}